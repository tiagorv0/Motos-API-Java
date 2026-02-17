package com.vazzoller.motosapi.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(ApiKeyProperties.class)
@Configuration
public class Config {
}
