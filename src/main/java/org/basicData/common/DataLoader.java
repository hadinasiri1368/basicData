package org.basicData.common;

import org.basicData.service.DataCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private DataCacheService dataCacheService;
    private boolean loaded = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!loaded) {
            dataCacheService.loadAllData();
            loaded = true;
        }
    }

}
