package org.basicData.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RedisDataCacheService implements CacheRepository {
    private final JPA jpa;
    @Autowired
    public RedisDataCacheService(@Lazy JPA jpa) {
        this.jpa = jpa;
    }
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.root-package-name}")
    private String rootPackageName;

    @Override
    public <T> List<T> findAll(Class<T> aClass) {
        String key = CommonUtils.getClassName(aClass.getName());
        Object rawObject = redisTemplate.opsForValue().get(key);

        if (rawObject != null) {
            try {
                List<T> list = objectMapper.convertValue(rawObject, objectMapper.getTypeFactory().constructCollectionType(List.class, aClass));
                return list;
            } catch (Exception e) {
                log.error("Error deserializing list from Redis", e);
            }
        }
        return null;
    }

    @Override
    public void saveOrUpdateToCache(String key, List<?> items) {
        redisTemplate.opsForValue().set(CommonUtils.getClassName(key), items);
    }

    @Override
    public void loadAllData() {
        clearCache();
        List<String> tableNames = getTablesName();
        for (String item : tableNames) {
            Class<?> entityClass = findEntityClassByTableName(item);
            if (entityClass != null) {
                List list = findAllFromDatabase(entityClass);
                saveOrUpdateToCache(entityClass.getName(), list);
            }
        }
    }

    @Override
    public <T> void refreshData(Class<T> aClass) {
        List<T> list = findAllFromDatabase(aClass);
        saveOrUpdateToCache(aClass.getName(), list);
    }

    private void clearCache() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    private List<String> getTablesName() {
        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'sbd'";
        return (List<String>) jpa.findByNativeQuery(sql);
    }

    private Class<?> findEntityClassByTableName(String tableName) {
        Reflections reflections = new Reflections(rootPackageName);
        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);

        for (Class<?> entityClass : entityClasses) {
            Table tableAnnotation = entityClass.getAnnotation(Table.class);
            if (tableAnnotation != null && tableAnnotation.name().replaceAll("[\\[\\]]", "").equals(tableName)) {
                return entityClass;
            }
        }
        return null;
    }

    private <T> List<T> findAllFromDatabase(Class<T> aClass) {
        Entity entity = aClass.getAnnotation(Entity.class);
        List<T> list = jpa.findByQuery("select entity from " + entity.name() + " entity");
        return list;
    }
}
