package com.ericramirezs.tasklistapp.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger to define the security scheme for API key authentication.
 * The API key is expected in the request header with the parameter name "X-API-KEY".
 */
@SecurityScheme(
        name = "api_key",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        paramName = "X-API-KEY"
)
@Configuration
public class SwaggerConfig {
}