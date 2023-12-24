package com.ll.todo20231222.global.app;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Getter
    public static String jwtSecretKey;

    @Value("${custom.jwt.secretKey}")
    public void setJwtSecretKey(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    @Getter
    private static long accessTokenExpirationSec;

    @Value("${custom.accessToken.expirationSec}")
    public void setJwtSecretKey(long accessTokenExpirationSec) {
        this.accessTokenExpirationSec = accessTokenExpirationSec;
    }

    @Getter
    private static String devFrontUrl;

    @Value("${custom.frontDevFrontUrl}")
    public void setDevFrontUrl(String devFrontUrl) { this.devFrontUrl = devFrontUrl; }

    @Getter
    private static String productionFrontUrl;

    @Value("${custom.frontProductionFrontUrl}")
    public void setProductionFrontUrl(String productionFrontUrl) { this.productionFrontUrl = productionFrontUrl; }
}
