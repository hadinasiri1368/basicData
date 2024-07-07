package org.basicData.common;

import org.basicData.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final CacheRepository dataCacheService;
    @Autowired
    public DataLoader(@Lazy CacheRepository dataCacheService) {
        this.dataCacheService = dataCacheService;
    }
    private boolean loaded = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!loaded) {
            dataCacheService.loadAllData();
            loaded = true;
        }
    }

}
