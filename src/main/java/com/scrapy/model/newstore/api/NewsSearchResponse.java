package com.scrapy.model.newstore.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.scrapy.common.ResponseJsonProperties;
import com.scrapy.common.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class NewsSearchResponse {

    @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_RESPONSE_REASON)
    private String reason;

    @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_RESPONSE_RESULT)
    private int result;

    @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_RESPONSE_RETURN_OBJECT)
    @JsonDeserialize(using = ReturnObjectDeserializer.class)
    private ReturnObject returnObject;

    @Getter
    @Setter
    public static class ReturnObject {
        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_RESPONSE_TOTAL_HITS)
        private int totalHits; // 검색된 전체 문서 건수를 반환합니다.

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_RESPONSE_DOCUMENT)
        private List<Document> documents;
    }


    @Getter
    @Setter
    public static class Documents {
        private List<Document> documents = new ArrayList<>();

       /* public void setDocuments(JsonNode jsonNode) {
            if (jsonNode.isArray()) {
                Iterator<JsonNode> elements = jsonNode.elements();
                while (elements.hasNext()) {
                    JsonNode node = elements.next();
                    documents.add(new Document(node));
                }
            }*//*
            else if(jsonNode.isObject()) {
                documents.add(new Document(jsonNode));
            }*//*
        }*/
    }



    @Getter
    @Setter
    public static class Document {
        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_NEWS_ID)
        private String newsId;

        private String title;

        private String content;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_PUBLISHED_AT)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private LocalDateTime publishedAt;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_ENVELOPED_AT)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private LocalDateTime envelopedAt;

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private LocalDateTime dateline;

        private String provider;

        private List<String> category;

        private String byline;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_CATEGORY_INCIDENT)
        private List<String> categoryIncident;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_HILIGHT)
        private String hilight;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_PROVIDER_LINK_PAGE)
        private String providerLinkPage;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_PRINTING_PAGE)
        private String printingPage;

        private String images;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_PROVIDER_CODE)
        private String publisherCode;
        private String providerNewsId;


        public void setByline(String byline) {
            String[] arr = byline.split(",");
            List<Map<String, String>> list = new ArrayList<>();
            Map<String, String> map = null;
            for (String item : arr) {
                if (item.contains("기자")) {
                    map = new HashMap<>();
                    map.put("name", item);
                } else if (item.contains("@")) {
                    map.put("mail", item);
                }

                list.add(map);
            }
            this.byline = list.size() > 0 ? JsonUtil.toJson(list) : "";
        }
    }


    public static class ReturnObjectDeserializer extends JsonDeserializer<ReturnObject> {
        @Override
        public ReturnObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            //String returnObjectString = p.getText(); // 이중 JSON 문자열
            String returnObjectString = p.readValueAs(String.class);
            return JsonUtil.fromJson(returnObjectString, ReturnObject.class);
        }
    }
}
