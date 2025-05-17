package com.scrapy.repository.config;

import com.scrapy.entity.config.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity, Long>, ConfigRepositoryCustom {
}
