package com.scrapy.model.newstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scrapy.common.NewsJsonProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class NewstoreRequestCode {

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_SEQ)
    private int seq;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_NEWS_ID)
    private String type;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_TITLE)
    private String title;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_SUBTITLE)
    private String subtitle;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_CONTENT)
    private String content;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_PUBLISHED_AT)
    private Date publishedAt;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_ENVELOPED_AT)
    private Date envelopedAt;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_DATELINE)
    private Date dateline;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_PROVIDER_ID)
    private String providerId;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_CATEGORY_ID)
    private String categoryId;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_HILIGHT)
    private String hilight;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_BYLINE)
    private String byline;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_IMAGES)
    private String images;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_PROVIDER_NEWS_ID)
    private String providerNewsId;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_PUBLISHER_CODE)
    private String publisherCode;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_PROVIDER_LINK_PAGE)
    private String providerLinkPage;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_PRINTING_PAGE)
    private String printingPage;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_UPDATE_TIME)
    private LocalDateTime updateTime;

    @JsonProperty(NewsJsonProperties.JSON_PROPERTY_CREATE_TIME)
    private LocalDateTime createTime;
}