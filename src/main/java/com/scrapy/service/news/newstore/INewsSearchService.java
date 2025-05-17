package com.scrapy.service.news.newstore;

import com.scrapy.model.newstore.api.NewsSearchResponse;

import java.util.List;

public interface INewsSearchService {

    NewsSearchResponse search(List<String> provider, String until, String from, String query);
}
