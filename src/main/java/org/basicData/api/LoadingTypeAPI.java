package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;

import org.basicData.model.LoadingType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class LoadingTypeAPI {
    @Autowired
    private GenericService<LoadingType> service;

    @PostMapping(path = "/api/loadingType/add")
    public Long addLoadingType(@RequestBody LoadingType loadingType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(loadingType, userId);
        return loadingType.getId();
    }

    @PostMapping(path = "/api/loadingType/edit")
    public Long editLoadingType(@RequestBody LoadingType loadingType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(loadingType, userId);
        return loadingType.getId();
    }

    @PostMapping(path = "/api/loadingType/remove/{id}")
    public Long removeLoadingType(@PathVariable Long id) {
        service.delete(id, LoadingType.class);
        return id;
    }

    @GetMapping(path = "/api/loadingType/{id}")
    public LoadingType getLoadingType(@PathVariable Long id) {
        return service.findOne(LoadingType.class, id);
    }

    @GetMapping(path = "/api/loadingType")
    public List<LoadingType> listLoadingType() {
        return service.findAll(LoadingType.class);
    }

}
