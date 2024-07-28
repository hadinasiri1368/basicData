package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.basicData.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class DataCacheAPI {
    @Autowired
    CacheRepository cacheRepository;

    @GetMapping(path = "/basicData/dataCache/refresh")
    public void refresh() {
        cacheRepository.loadAllData();
    }
}
