package com.scrapy.service.login;

public interface LoginService {

    void login(String userId, String id, String password) throws Exception;

    void logout(String userId);
}