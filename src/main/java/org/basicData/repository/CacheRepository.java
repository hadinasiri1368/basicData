package org.basicData.repository;

import java.util.List;

public interface CacheRepository {
    <T> List<T> findAll(Class<T> aClass);
    void saveOrUpdateToCache(String key, List<?> items);
    void loadAllData();
    <T> void refreshData(Class<T> aClass);
}
