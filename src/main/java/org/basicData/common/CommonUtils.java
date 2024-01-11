package org.basicData.common;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CommonUtils {
    public static Long getUserId(String token) {
        try {
            String url = ApplicationProperties.getServiceUrlAuthentication() + "/getUserId";
            url += "?token=" + token;
            return callService(url, HttpMethod.GET, null, null, Long.class, null);
        } catch (Exception e) {
            log.error("checkValidation error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String getToken(HttpServletRequest request) {
        if (CommonUtils.isNull(request.getHeader("Authorization")))
            return null;
        return request.getHeader("Authorization").replaceAll("Bearer ", "");
    }

    public static boolean isNull(Object o) {
        if (o instanceof String) {
            if (o == null ||
                    ((String) o).isEmpty() ||
                    ((String) o).isBlank() ||
                    ((String) o).length() == 0 ||
                    ((String) o).toLowerCase().trim().equals("null")
            )
                return true;
            return false;
        }
        return o == null ? true : false;
    }

    public static String checkValidation(String token, String targetUrl) {
        try {
            String url = ApplicationProperties.getServiceUrlAuthentication() + "/checkValidationToken";
            url += "?token=" + token + "&url=" + targetUrl;
            String returnValue = callService(url, HttpMethod.GET, null, null, String.class, null);
            if (returnValue.equals("token is ok"))
                return null;
            return returnValue;
        } catch (Exception e) {
            log.error("checkValidation error: " + e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static <T> T callService(String url, HttpMethod httpMethod, HttpHeaders headers, Object body, Class<T> aClass, Map<String, Object> params) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(body, headers);
        if (CommonUtils.isNull(params))
            params = new HashMap<>();
        HttpEntity<T> response = restTemplate.exchange(url, httpMethod, httpEntity, aClass, params);
        return response.getBody();
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

    public static void setNull(Object entity) throws Exception {
        Class cls = Class.forName(entity.getClass().getName());
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length());
            Method m = entity.getClass().getMethod("get" + name);
            Object o = m.invoke(entity);
            if (CommonUtils.isNull(o)) {
                Method method = entity.getClass().getMethod("set" + name, field.getType());
                method.invoke(entity, field.getType().cast(null));
            }
        }
    }
}
