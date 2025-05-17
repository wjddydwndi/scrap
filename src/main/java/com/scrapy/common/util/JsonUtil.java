package com.scrapy.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.scrapy.common.exception.ServiceException;
import com.scrapy.common.log.Log;

import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtil() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);//타임스탬프 비활성화
    }

    public static <T> String toJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            Log.error("failed to convert object to json, e : {}", e.getMessage());
            throw new RuntimeException("failed to convert object to json");
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {

        try {
            return objectMapper.readValue(json, clazz);

        } catch (JsonProcessingException e) {
            Log.error("JsonToObjectConverter json={}, exception={}", json, e.getMessage());
            throw new ServiceException("failed to read json");
        }
    }

    public static <T> T fromResponseJson(String json, Class<T> clazz) {

        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return objectMapper.convertValue(json, clazz);

        } catch (Exception e) {
            Log.error("JsonToObjectConverter json={}, exception={}", json, e.getMessage());
            throw new ServiceException("failed to read json");
        }
    }
}
