package com.scrapy.entity.news;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "t_newstore_reqeust_code")
@Getter
public class NewstoreRequestCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_id")
    private String codeId;

    @Column(name = "description")
    private String description;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_time")
    private Date createTime;
}
