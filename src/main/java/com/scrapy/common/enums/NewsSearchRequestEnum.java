package com.scrapy.common.enums;

public enum NewsSearchRequestEnum {

    code_news_search_request_sort_date_asc("asc"),
    code_news_search_request_sort_date_desc("desc");

    String code;

    NewsSearchRequestEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
