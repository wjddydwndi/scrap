package com.scrapy.common.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrapy.common.enums.ResponseCode;
import com.scrapy.common.exception.ServiceException;
import com.scrapy.common.log.Log;
import com.scrapy.common.util.JsonUtil;
import com.scrapy.model.newstore.api.NewsSearchRequest;
import com.scrapy.model.Response;
import com.scrapy.model.newstore.api.NewsSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SearchRestTemplate extends AbstractRestTemplate implements ISearchRestTemplate {
    private final String urlNewsSearch = "https://www.newstore.or.kr/api-newstore/v1/search/newsAllList.json";// 뉴스 검색

    @Value("${newstore.api-key}")
    private String apiKey;

    @Value("${newstore.access-key}")
    private String accessKey;

    @Override
    public Response searchByQuery(NewsSearchRequest newsSearchRequest) {

        try {
            newsSearchRequest.setApiKey(apiKey);
            newsSearchRequest.setAccessKey(accessKey);

            HttpHeaders headers = new HttpHeaders();

            // insert
            ResponseEntity<String> responseEntity = post(urlNewsSearch, headers, JsonUtil.toJson(newsSearchRequest));

            // insert
            NewsSearchResponse newsSearchResponse = JsonUtil.fromJson(responseEntity.getBody(), NewsSearchResponse.class);
            return new Response(ResponseCode.response_success.getCode(), "OK", newsSearchResponse);

        } catch(Exception e) {
            Log.error("failed to request, api error, {} e={}", urlNewsSearch, e.getMessage());
            throw new ServiceException("failed to request, api error");
        }
    }
}