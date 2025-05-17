package com.scrapy.service.news.newstore;

import com.scrapy.common.enums.NewsSearchRequestEnum;
import com.scrapy.common.log.Log;
import com.scrapy.common.search.SearchRestTemplate;
import com.scrapy.model.newstore.api.NewsSearchRequest;
import com.scrapy.model.Response;
import com.scrapy.model.newstore.api.NewsSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class INewsSearchServiceImpl implements INewsSearchService {

    @Autowired private SearchRestTemplate searchRestTemplate;
    @Value("${newstore.return-from}") private int returnFrom;

    @Value("${newstore.return-size}") private int returnSize;

    @Override
    public NewsSearchResponse search(List<String>provider, String until, String from, String query) {

        NewsSearchRequest newsSearchRequest = new NewsSearchRequest();

        NewsSearchRequest.PublishedAt publishedAt = NewsSearchRequest.PublishedAt.builder().from(from).until(until).build();

        NewsSearchRequest.Argument argument = NewsSearchRequest.Argument.builder()
                .provider(provider)
                .publishedAt(publishedAt)
                .query(query)
                .sort(sortDesc())
                .returnFrom(returnFrom)
                .returnSize(returnSize)
                .build();
        newsSearchRequest.setArgument(argument);

        Response response = searchRestTemplate.searchByQuery(newsSearchRequest);
        Log.info("수집 데이터 response code : {}, message : {}, data : {}", response.getCode(), response.getMessage(), response.getData());

        return (NewsSearchResponse) response.getData();
    }

    private NewsSearchRequest.Sort sortDesc() {
        return NewsSearchRequest.Sort.builder()
                .date(NewsSearchRequestEnum.code_news_search_request_sort_date_desc.getCode())
                .build();
    }

    private NewsSearchRequest.Sort sortAsc() {
        return NewsSearchRequest.Sort.builder()
                .date(NewsSearchRequestEnum.code_news_search_request_sort_date_asc.getCode())
                .build();
    }
}
