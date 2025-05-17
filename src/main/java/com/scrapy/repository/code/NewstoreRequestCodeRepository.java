package com.scrapy.repository.code;

import com.scrapy.entity.news.NewstoreRequestCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewstoreRequestCodeRepository extends JpaRepository<NewstoreRequestCodeEntity, Long>, NewstoreRequestCodeRepositoryCustom {
}
