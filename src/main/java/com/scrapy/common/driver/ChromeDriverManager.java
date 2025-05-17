package com.scrapy.common.driver;

import com.scrapy.common.log.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

public class ChromeDriverManager {

    private Map<String, WebDriver> webDriverMap;

    public void setup(String key, String url) {
        if (webDriverMap.containsKey(key)) {
            Log.info("chrome driver exist : {}", key);
            quit(key);
        }

        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(url);

        webDriverMap.put(key, webDriver);
        Log.info("chrome driver setup : {} {}", key, url);
    }

    public WebDriver get(String key) {
        if (!webDriverMap.containsKey(key)) {
            Log.error("chrome driver not found : {}", key);
            return null;
        }

        return webDriverMap.get(key);
    }

    public void quit(String key) {
        if (!webDriverMap.containsKey(key)) {
            Log.error("chrome driver not found : {}", key);
            return;
        }

        WebDriver webDriver = webDriverMap.get(key);
        webDriver.quit();
        webDriverMap.remove(key);

        Log.info("chrome driver quit : {}", key);
    }


}
