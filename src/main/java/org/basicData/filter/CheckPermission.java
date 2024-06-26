package org.basicData.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.basicData.dto.ExceptionDto;
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
            if (!request.getMethod().equals("OPTIONS")) {
                authenticationServiceProxy.checkValidationToken(token, uuid, request.getRequestURI());
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.info("RequestURL:" + request.getRequestURL() + "  UUID=" + uuid + "  ServiceUnauthorized:" + e.getMessage().split("]:")[1]);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(convertObjectToJson(ExceptionDto.builder()
                    .errorMessage("unauthorized exception")
                    .errorCode(HttpStatus.UNAUTHORIZED.value())
                    .uuid(request.getHeader("X-UUID"))
                    .errorStatus(HttpStatus.UNAUTHORIZED.value())
                    .build()));
        }
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
