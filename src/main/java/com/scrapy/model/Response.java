package com.scrapy.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {

    private String code;
    private String message;
    private Object data;

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
