package com.example.demospringbootstarter.config;


import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(OkhttpProperties.class)
public class OkhttpAutoConfiguration {

    @Autowired
    private OkhttpProperties okhttpProperties;

    @Bean
    public OkHttpClient okHttpClient(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(okhttpProperties.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(okhttpProperties.getReadTimeout(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(connectionPool())
                .build();
        return httpClient;
    }

    @Bean
    public ConnectionPool connectionPool() {
        return new ConnectionPool(okhttpProperties.getPoolSize(), 5, TimeUnit.MINUTES);
    }
}
