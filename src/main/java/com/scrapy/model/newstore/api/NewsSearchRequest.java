package com.scrapy.model.newstore.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scrapy.common.RequestJsonProperties;
import com.scrapy.common.ResponseJsonProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
* 아래 링크 참고
* https://www.newstore.or.kr/store/prodct/license-news-search/license-api-list.do#n
* */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsSearchRequest {
    private String apiKey; // API 요청 시 검증을 위한 키 값
    private Argument argument;

    @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_ACCESS_KEY)
    private String accessKey;

    @Getter
    @Setter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Argument {
        private String query; // 검색 질의어를 지정합니다.(검색어 없이 검색 가능)

        private Sort sort; // 검색된 뉴스기사의 정렬 방식을 지정합니다.
        
        private List<String> provider; // 언론사를 지정 ,로 나열

        private List<String> category; // 한국언론진흥재단에서 지정한 뉴스 통합 분류 체계 값을 조건으로 지정 ,로 나열

        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_CATEGORY_INCIDENT)
        private String categoryIncident; // 한국언론진흥재단에서 지정한 뉴스 사건/사고 분류체계 값을 조건으로 지정합니다. ( ‘ , ’ 를 구분자로 나열할 수 있습니다)
        
        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_PROVIDER_SUBJECT)
        private String providerSubject; // 언론사에서 제공한 News의 주제 분류 값을 조건으로 지정합니다. ( ‘ , ’ 를 구분자로 나열할 수 있습니다)
        
        private String byline; // 기자의 이름을 조건으로 지정합니다. ※ 언론사에서 기자 이름을 제공한 News인 경우에만 검색이 가능합니다.

        private int hilight;// 기사에서 검색어가 포함된 영역을 찾아 지정한 범위(최대 200자)를 출력 결과로 제공합니다.

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_ARGUMENT_PUBLISHED_AT)
        private PublishedAt publishedAt;

        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_RETURN_FROM)
        private int returnFrom; // 검색된 결과 목록 반환 시작 위치 지정
        
        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_RETURN_SIZE)
        private int returnSize; // 검색된 결과 목록 반환 개수 지정

        private Fields fields;
    }


    @Getter
    @Setter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PublishedAt {
        private String from;    // 검색 시작일 [yyyy-MM-dd]
        private String until;   // 검색 종료일 [yyyy-MM-dd]
    }



    @Getter
    @Setter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Sort {
        private String date;

        private String title;

        private String _score;

        private String byline;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_PROVIDER_CODE)
        private String providerCode;
    }

    @Getter
    @Setter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Fields {
        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_NEWS_ID)
        private String newsId;
        
        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_PUBLISHED_AT)
        private String publishedAt;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_ENVELOPED_AT)
        private String envelopedAt;

        private String title;

        private String content;
        
        private String provider;

        private String byline;

        private String category;

        private String providerNewsId;

        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_PROVIDER_LINK_PAGE)
        private String providerLinkPage;
        
        @JsonProperty(ResponseJsonProperties.JSON_PROPERTY_NEWS_SEARCH_REQUEST_FIELDS_PRINTING_PAGE)
        private String printingPage;
        
        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_SUBJECT_INFO)
        private String subjectInfo;

        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_SUBJECT_INFO1)
        private String subjectInfo1;

        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_SUBJECT_INFO2)
        private String subjectInfo2;
        
        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_SUBJECT_INFO3)
        private String subjectInfo3;

        @JsonProperty(RequestJsonProperties.JSON_PROPERTY_NEWS_SEARCH_SUBJECT_INFO4)
        private String subjectInfo4;
    }

}
