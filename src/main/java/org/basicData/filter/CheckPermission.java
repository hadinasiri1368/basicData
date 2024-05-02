package org.basicData.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.basicData.common.CommonUtils;
import org.basicData.service.AuthenticationServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CheckPermission extends OncePerRequestFilter implements Filter {

    private AuthenticationServiceProxy authenticationServiceProxy;

    public CheckPermission(AuthenticationServiceProxy authenticationServiceProxy) {
        this.authenticationServiceProxy = authenticationServiceProxy;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = CommonUtils.getToken(request);
        if (!CommonUtils.isNull(token)) {
            String returnValue = authenticationServiceProxy.checkValidationToken(token, request.getRequestURI());
            if (CommonUtils.isNull(returnValue)) {
                try {
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    response.getWriter().write(e.getMessage());
                }
            } else {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.getWriter().write(returnValue);
            }
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write("token is null");
        }
    }
}
