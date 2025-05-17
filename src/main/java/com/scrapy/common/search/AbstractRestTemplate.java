package com.scrapy.common.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.scrapy.common.log.Log;
import com.scrapy.common.util.JsonUtil;
import com.scrapy.model.newstore.api.NewsSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public abstract class AbstractRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    public <T> ResponseEntity<String> post(String url, HttpHeaders headers, String body) {

        headers.addAll(commonHeaders());
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Log.info("[REQ] POST {} headers={} body={}", url, headers, body);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        Log.info("[RES] POST {} {} {}", url, responseEntity.getStatusCode(), responseEntity.getBody());

        return responseEntity;
    }

    public ResponseEntity get(String url, HttpHeaders headers, String jsonBody) {

        headers.addAll(commonHeaders());
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        Log.info("[REQ] GET {} headers={} body={}", url, headers, jsonBody);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, request);
        Log.info("[RES] GET {} {} {}", url, responseEntity.getStatusCode(), responseEntity.getBody());

        NewsSearchResponse newsSearchResponse = JsonUtil.fromResponseJson(responseEntity.getBody(), NewsSearchResponse.class);

        return new ResponseEntity(newsSearchResponse, HttpStatus.OK);
    }

    public static String toQueryString(Object dto) {
        StringBuilder queryString = new StringBuilder();
        try {
            Field[] fields = dto.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);  // private 필드 접근 허용
                Object value = field.get(dto);
                if (value != null) {
                    if (queryString.length() > 0) {
                        queryString.append("&");
                    }
                    queryString.append(URLEncoder.encode(field.getName(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to convert DTO to Query String", e);
        }
        return queryString.toString();
    }


    private HttpHeaders commonHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("Content-Type","application/json;charset=UTF-8");
        return headers;
    }
}
