package com.scrapy.repository.news;

import com.scrapy.entity.news.NewstoreNewsDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewstoreNewsDataRepository extends JpaRepository<NewstoreNewsDataEntity, Long>, NewstoreNewsDataRepositoryCustom {
}
