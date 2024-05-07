package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.BaseInfoGood;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class BaseInfoGoodAPI {
    @Autowired
    private GenericService<BaseInfoGood> service;

    @PostMapping(path = "/api/baseInfoGood/add")
    public Long addBaseInfoGood(@RequestBody BaseInfoGood baseInfoGood, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(baseInfoGood, userId);
        return baseInfoGood.getId();
    }

    @PutMapping(path = "/api/baseInfoGood/edit")
    public Long editBaseInfoGood(@RequestBody BaseInfoGood baseInfoGood, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(baseInfoGood, userId, BaseInfoGood.class);
        return baseInfoGood.getId();
    }

    @DeleteMapping(path = "/api/baseInfoGood/remove/{id}")
    public Long removeBaseInfoGood(@PathVariable Long id) {
        service.delete(id, BaseInfoGood.class);
        return id;
    }

    @GetMapping(path = "/api/baseInfoGood/{id}")
    public BaseInfoGood getBaseInfoGood(@PathVariable Long id) {
        return service.findOne(BaseInfoGood.class, id);
    }

    @GetMapping(path = "/api/baseInfoGood")
    public Page<BaseInfoGood> listBaseInfoGood(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(BaseInfoGood.class, page, size);
    }

}
