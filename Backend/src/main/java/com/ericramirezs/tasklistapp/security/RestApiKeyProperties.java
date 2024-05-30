package com.ericramirezs.tasklistapp.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("task-list-app.http")
public class RestApiKeyProperties {

    private String authToken;

}
