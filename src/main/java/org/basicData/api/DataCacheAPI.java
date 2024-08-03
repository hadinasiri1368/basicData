package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.basicData.repository.CacheRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class DataCacheAPI {
    final
    CacheRepository cacheRepository;

    public DataCacheAPI(CacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    @GetMapping(path = "/basicData/dataCache/refresh")
    public void refresh() {
        cacheRepository.loadAllData();
    }
}
