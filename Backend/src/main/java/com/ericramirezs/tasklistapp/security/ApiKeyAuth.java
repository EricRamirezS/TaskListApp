package com.ericramirezs.tasklistapp.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Custom {@link AbstractAuthenticationToken} implementation that represents an API key-based authentication.
 */
public class ApiKeyAuth extends AbstractAuthenticationToken {

    private final String apiKey;

    /**
     * Constructs an {@link ApiKeyAuth} object with the provided API key and granted authorities.
     *
     * @param apiKey      the API key used for authentication.
     * @param authorities the collection of granted authorities for this authentication token.
     */
    public ApiKeyAuth(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    /**
     * Returns the credentials, which in this case is always {@code null} as the API key is used as the principal.
     *
     * @return {@code null}, since there are no credentials.
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * Returns the principal, which is the API key.
     *
     * @return the API key used for authentication.
     */
    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
