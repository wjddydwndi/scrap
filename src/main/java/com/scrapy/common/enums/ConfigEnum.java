package com.scrapy.common.enums;

public enum ConfigEnum {

    code_config_mail_properties_from("mail_properties_from"),
    code_config_mail_properties_cc("mail_properties_cc"),
    code_config_mail_properties_max_attempt("mail_properties_max_attempt");

    String code;

    ConfigEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
