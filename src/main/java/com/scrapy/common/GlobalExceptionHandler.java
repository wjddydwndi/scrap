package com.scrapy.common;


import com.scrapy.common.enums.ResponseCode;
import com.scrapy.common.exception.ServiceException;
import com.scrapy.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ServiceException.class)
    public ResponseEntity ServiceException(ServiceException e) {
        return new ResponseEntity(new Response(ResponseCode.err_response_internal_server.getCode(), e.getMessage())
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
