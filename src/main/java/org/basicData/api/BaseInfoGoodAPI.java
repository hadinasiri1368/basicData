package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.BaseInfoGood;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BaseInfoGoodAPI {
    @Autowired
    private GenericService<BaseInfoGood> service;

    @PostMapping(path = "/api/baseInfoGood/add")
    public Long addBaseInfoGood(@RequestBody BaseInfoGood baseInfoGood, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(baseInfoGood, userId);
        return baseInfoGood.getId();
    }

    @PostMapping(path = "/api/baseInfoGood/edit")
    public Long editBaseInfoGood(@RequestBody BaseInfoGood carCapacity, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(carCapacity, userId);
        return carCapacity.getId();
    }

    @PostMapping(path = "/api/baseInfoGood/remove/{id}")
    public Long removeBaseInfoGood(@PathVariable Long id) {
        service.delete(id, BaseInfoGood.class);
        return id;
    }

    @GetMapping(path = "/api/baseInfoGood/{id}")
    public BaseInfoGood getBaseInfoGood(@PathVariable Long id) {
        return service.findOne(BaseInfoGood.class, id);
    }

    @GetMapping(path = "/api/baseInfoGood")
    public List<BaseInfoGood> listBaseInfoGood() {
        return service.findAll(BaseInfoGood.class);
    }

}
