package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;

import org.basicData.model.ProvinceCity;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProvinceCityAPI {
    @Autowired
    private GenericService<ProvinceCity> service;

    @PostMapping(path = "/api/provinceCity/add")
    public Long addProvinceCity(@RequestBody ProvinceCity provinceCity, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(provinceCity, userId);
        return provinceCity.getId();
    }

    @PostMapping(path = "/api/provinceCity/edit")
    public Long editProvinceCity(@RequestBody ProvinceCity packingType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(packingType, userId);
        return packingType.getId();
    }

    @PostMapping(path = "/api/provinceCity/remove/{id}")
    public Long removeProvinceCity(@PathVariable Long id) {
        service.delete(id, ProvinceCity.class);
        return id;
    }

    @GetMapping(path = "/api/provinceCity/{id}")
    public ProvinceCity getProvinceCity(@PathVariable Long id) {
        return service.findOne(ProvinceCity.class, id);
    }

    @GetMapping(path = "/api/provinceCity")
    public List<ProvinceCity> listProvinceCity() {
        return service.findAll(ProvinceCity.class);
    }

}
