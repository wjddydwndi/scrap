package com.scrapy.common.search;

import com.scrapy.model.newstore.api.NewsSearchRequest;
import com.scrapy.model.Response;

public interface ISearchRestTemplate {
    Response searchByQuery(NewsSearchRequest newsSearchRequest);
}
