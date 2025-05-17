package com.scrapy.entity.news;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_newstore_news_data")
@Getter
public class NewstoreNewsDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "news_id")
    private String newsId;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "content")
    private String content;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "enveloped_at")
    private LocalDateTime envelopedAt;

    @Column(name = "dateline")
    private LocalDateTime dateline;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "hilight")
    private String hilight;

    @Column(name = "byline")
    private String byline;

    @Column(name = "images")
    private String images;

    @Column(name = "provider_news_id")
    private String providerNewsId;

    @Column(name = "publisher_code")
    private String publisherCode;

    @Column(name = "provider_link_page")
    private String providerLinkPage;

    @Column(name = "printing_page")
    private String printingPage;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}
