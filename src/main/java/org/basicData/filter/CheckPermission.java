package org.basicData.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.basicData.service.AuthenticationServiceProxy;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class CheckPermission extends OncePerRequestFilter implements Filter {
    private AuthenticationServiceProxy authenticationServiceProxy;

    public CheckPermission(AuthenticationServiceProxy authenticationServiceProxy) {
        this.authenticationServiceProxy = authenticationServiceProxy;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uuid = request.getHeader("X-UUID");
        String token = CommonUtils.getToken(request);
        try {
            authenticationServiceProxy.checkValidationToken(token, uuid, request.getRequestURI());
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.info("RequestURL:" + request.getRequestURL() + "  UUID=" + uuid + "  ServiceUnauthorized:" + e.getMessage().split("]:")[1]);
            throw HttpClientErrorException.Unauthorized.create(e.getMessage().split("]:")[1], null, null, null, null, null);
        }
    }
}
