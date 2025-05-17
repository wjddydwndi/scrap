package com.scrapy.common.enums;

public enum ResponseCode {

    response_success("200"),
    err_response_internal_server("500");

    private String code;

    ResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
