package com.scrapy.repository.news;

import com.scrapy.model.newstore.NewstoreNewsData;

import java.util.List;

public interface NewstoreNewsDataJdbcRepository {

    void insertNewstoreNewsData(List<NewstoreNewsData> list);
}
