package com.ericramirezs.tasklistapp.annotation;

import com.ericramirezs.tasklistapp.security.ApiKeyAuth;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockAuthSecurityContextFactory implements WithSecurityContextFactory<WithMockAuth> {
    @Override
    public SecurityContext createSecurityContext(WithMockAuth annotation) {
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new ApiKeyAuth("test", AuthorityUtils.NO_AUTHORITIES));
        return context;
    }
}
