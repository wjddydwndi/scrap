package com.scrapy.repository.news;

import com.scrapy.entity.news.NewstoreNewsDataEntity;

// Custom Interface
public interface NewstoreNewsDataRepositoryCustom {

    NewstoreNewsDataEntity findTopByOrderByCreateTimeDesc();
}
