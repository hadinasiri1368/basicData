package org.basicData.common;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class CommonUtils {
    public static Long getUserId(String token) {
        return 1L;
    }

    public static String getToken(HttpServletRequest request) {
        if (CommonUtils.isNull(request.getHeader("Authorization")))
            return null;
        return request.getHeader("Authorization").replaceAll("Bearer ", "");
    }

    public static boolean isNull(Object o) {
        if (o instanceof String) {
            if (o == null || ((String) o).isEmpty() || ((String) o).isBlank() || ((String) o).length() == 0)
                return true;
            return false;
        }
        return o == null ? true : false;
    }

    public static String checkValidation(String url, String token) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            HttpEntity<String> responce = restTemplate.getForEntity(url, String.class);
            return responce.getBody();
        } catch (Exception e) {
            return null;
        }
    }
    public static Long longValue(Object number) {
        if (isNull(number))
            return null;
        else if (number instanceof Number)
            return ((Number) number).longValue();
        else
            try {
                return Long.valueOf(number.toString().trim());
            } catch (NumberFormatException e) {
                return null;
            }
    }
}
