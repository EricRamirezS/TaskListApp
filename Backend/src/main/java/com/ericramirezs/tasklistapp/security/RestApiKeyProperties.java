package com.ericramirezs.tasklistapp.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties class that holds the API key used for authentication.
 * The properties are prefixed with "task-list-app.http" in the application's configuration file.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("task-list-app.http")
public class RestApiKeyProperties {
    /**
     * The API key used for authenticating requests.
     */
    private String authToken;

}
