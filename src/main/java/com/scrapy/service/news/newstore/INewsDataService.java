package com.scrapy.service.news.newstore;

import java.time.LocalDateTime;
import java.util.Date;

public interface INewsDataService {

    String getLatestNewsDateString();

    LocalDateTime getLatestNewsDate();
}
