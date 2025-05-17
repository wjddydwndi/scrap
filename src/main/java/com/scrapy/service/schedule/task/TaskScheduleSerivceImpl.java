package com.scrapy.service.schedule.task;

import com.scrapy.common.NewsoreCodeProperties;
import com.scrapy.common.log.Log;
import com.scrapy.common.util.CommonUtil;
import com.scrapy.common.util.DateFormatUtil;
import com.scrapy.common.util.JsonUtil;
import com.scrapy.entity.news.NewstoreRequestCodeEntity;
import com.scrapy.entity.schedule.TaskScheduleEntity;
import com.scrapy.model.newstore.NewstoreNewsData;
import com.scrapy.model.newstore.api.NewsSearchResponse;
import com.scrapy.model.schedule.TaskSchedule;

import com.scrapy.repository.code.NewstoreRequestCodeRepository;
import com.scrapy.repository.news.NewstoreNewsDataJdbcRepository;
import com.scrapy.repository.news.NewstoreNewsDataRepository;
import com.scrapy.repository.schedule.TaskScheduleRepository;
import com.scrapy.service.news.newstore.INewsDataService;
import com.scrapy.service.news.newstore.INewsSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskScheduleSerivceImpl implements ITaskScheduleService {

    private final TaskScheduleRepository taskScheduleRepository;

    private final NewstoreNewsDataRepository newstoreNewsDataRepository;

    private final NewstoreNewsDataJdbcRepository newstoreNewsDataJdbcRepository;

    private final NewstoreRequestCodeRepository newstoreRequestCodeRepository;

    private final INewsSearchService newsSearchService;

    private final INewsDataService newsDataService;

    //private final NewsRedisService newsRedisService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    @Override
    public void execute() {

        LocalDateTime executionTime = LocalDateTime.now();
        Log.info("execute, 작업 스케줄링 시작... startTime : {}", executionTime.format(formatter));

        // 매 1분마다 실행. 1시간 이내 실행이 안된 작업목록도 가져와서 실행.
        // 1. 현재 시간 기준 1시간 이내 실행이 안된 작업목록도 가져옴. reserve_time 기준 / 업체별(?)
        List<TaskSchedule> list = getReservedTask(executionTime.toLocalTime());

        Log.info("execute, 작업 목록 : {} 건", list.size());

        // 2. 작업 수행.
        for (TaskSchedule task : list) {
            Log.info("execute, taskCode : {}", task.getTaskCode());

            if (!executeTask(task)) {
                Log.error("execute, taskCode : {} fail", task.getTaskCode());
                // 메일/sms 발송 추가 필요.
            } else {
                Log.info("execute, taskCode : {} complete", task.getTaskCode());
            }
        }

        String completeTime = currentTime();
        Log.info("execute, 작업 스케줄링 종료... completeTime : {}", completeTime);
    }

    private String currentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(formatter);
    }

    private List<TaskSchedule> getReservedTask(LocalTime localTime) {

        long offsetHours = 1;
        LocalTime truncatedTime = localTime.withSecond(0).withNano(0);
        LocalTime standardTime = truncatedTime.minusMinutes(offsetHours);

        List<TaskScheduleEntity> list = taskScheduleRepository.findByReserveTime(standardTime);

        return list.stream().map(entity -> TaskSchedule.builder()
                .seq(entity.getSeq())
                .taskCode(entity.getTaskCode())
                .reserveTime(entity.getReserveTime())
                .execute(entity.getExecute())
                .isActive(entity.isActive())
                .isComplete(entity.isComplete())
                .description(entity.getDescription())
                .expireTime(entity.getExpireTime())
                .createTime(entity.getCreateTime()).build()).collect(Collectors.toList());
    }

    private boolean executeTask(TaskSchedule task) {

        if (CommonUtil.isEmpty(task.getExecute())) {
            Log.error("executeTask(), 작업할 데이터 없음. code={}", task.getTaskCode());
            return false;
        }

        Map<String, Object> executeMap = JsonUtil.fromJson(task.getExecute(), Map.class);

        if (executeMap.containsKey("provider") && executeMap.containsKey("query")) { // 매체명
            List<String> providerList = (List<String>) executeMap.get("provider");
            String query = String.valueOf(executeMap.get("query"));

            // from
            String from = newsDataService.getLatestNewsDateString();

            // until
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);
            String until = DateFormatUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");

            // 조회
            NewsSearchResponse newsSearchResponse = newsSearchService.search(providerList, until, from, query);
            if (CommonUtil.isEmpty(newsSearchResponse.getReturnObject()) || CommonUtil.isEmpty(newsSearchResponse.getReturnObject().getDocuments())) {
                Log.info("executeTask(), 조회된 데이터 건수 없음.");
                return true;
            }

            List<NewsSearchResponse.Document> documentList = newsSearchResponse.getReturnObject().getDocuments();

            // 등록
            Optional<NewsSearchResponse.Document> optional = documentList.stream().max(Comparator.comparing(NewsSearchResponse.Document::getPublishedAt));
            if (optional.isPresent()) {

                LocalDateTime latestNewsDate = (LocalDateTime) newsDataService.getLatestNewsDate();
                if (optional.get().getPublishedAt().isBefore(latestNewsDate)) {
                    // 가장 최근 published 조회해서 크면 insert
                    List<NewsSearchResponse.Document> newsSearchResponseDocument = documentList.stream().filter(x -> x.getPublishedAt().isBefore(latestNewsDate)).collect(Collectors.toList());

                    List<NewstoreNewsData> newstoreNewsDataList= transResponseToNewData(newsSearchResponseDocument);

                    newstoreNewsDataJdbcRepository.insertNewstoreNewsData(newstoreNewsDataList);

                    Log.info("executeTask(), {} 건 데이터 수집 완료.", newstoreNewsDataList.size());
                }
            }
            return true;
        }

        return false;
    }

    private List<NewstoreNewsData> transResponseToNewData(List<NewsSearchResponse.Document> list) {

        List<NewstoreNewsData> instDataList = new ArrayList<>();

        for (NewsSearchResponse.Document item :list) {

            NewstoreNewsData newstoreNewsData = new NewstoreNewsData();
            newstoreNewsData.setNewsId(item.getNewsId());
            newstoreNewsData.setTitle(item.getTitle());
            newstoreNewsData.setContent(item.getContent());
            newstoreNewsData.setPublishedAt(item.getPublishedAt());
            newstoreNewsData.setEnvelopedAt(item.getEnvelopedAt());
            newstoreNewsData.setDateline(item.getDateline());

            NewstoreRequestCodeEntity providerRequestCodeEntity = newstoreRequestCodeRepository.findByTypeAndCodeName(NewsoreCodeProperties.CODE_REQUEST_CODE_PROVIDER, item.getProvider());
            /*if (CommonUtil.isEmpty(providerRequestCodeEntity)) {
                Log.error("설정값 확인 필요. t_newstore_reqeust_code is empty, code={}, code_name={}", NewsoreCodeProperties.CODE_REQUEST_CODE_PROVIDER, item.getProvider());
                continue;
            }
            newstoreNewsData.setProviderId(providerRequestCodeEntity.getCodeId());*/
            newstoreNewsData.setProviderId("TEST");

            NewstoreRequestCodeEntity categoryRequestCodeEntity = newstoreRequestCodeRepository.findByTypeAndCodeName(NewsoreCodeProperties.CODE_REQUEST_CODE_PROVIDER, item.getProvider());
            //newstoreNewsData.setCategoryId(categoryRequestCoce.getCodeId());

            newstoreNewsData.setHilight(item.getHilight());
            newstoreNewsData.setByline(item.getByline());
            newstoreNewsData.setImages(item.getImages());
            newstoreNewsData.setProviderNewsId(item.getProviderNewsId());
            newstoreNewsData.setPublisherCode(item.getPublisherCode());
            newstoreNewsData.setProviderLinkPage(item.getProviderLinkPage());
            newstoreNewsData.setPrintingPage(item.getPrintingPage());

            instDataList.add(newstoreNewsData);
        }

        return instDataList;
    }

}
