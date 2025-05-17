package com.scrapy.common.enums;

public enum CommonEnum {

    code_database_scrapy("scrapy");

    String code;

    CommonEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
