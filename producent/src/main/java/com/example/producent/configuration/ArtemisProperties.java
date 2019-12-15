package com.example.producent.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Data
@Validated
@ConfigurationProperties("app.artemis")
@EnableConfigurationProperties(ArtemisProperties.class)
public class ArtemisProperties {
    private String brokerUrl;
    private String user;
    private String password;
    private String queue;
    private String queueDurable;
    private String topic;
    private String topicDurable;
    private String clientId;
}


