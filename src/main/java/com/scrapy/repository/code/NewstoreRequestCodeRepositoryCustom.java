package com.scrapy.repository.code;

import com.scrapy.entity.news.NewstoreRequestCodeEntity;

// Custom Interface
public interface NewstoreRequestCodeRepositoryCustom {
    NewstoreRequestCodeEntity findByTypeAndCodeName(String type, String codeName);
}
