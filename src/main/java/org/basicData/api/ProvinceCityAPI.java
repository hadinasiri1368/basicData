package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;

import org.basicData.model.ProvinceCity;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class ProvinceCityAPI {
    @Autowired
    private GenericService<ProvinceCity> service;

    @PostMapping(path = "/basicData/provinceCity/add")
    public Long addProvinceCity(@RequestBody ProvinceCity provinceCity, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(provinceCity, userId);
        return provinceCity.getId();
    }

    @PutMapping(path = "/basicData/provinceCity/edit")
    public Long editProvinceCity(@RequestBody ProvinceCity packingType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(packingType, userId, ProvinceCity.class);
        return packingType.getId();
    }

    @DeleteMapping(path = "/basicData/provinceCity/remove/{id}")
    public Long removeProvinceCity(@PathVariable Long id) {
        service.delete(id, ProvinceCity.class);
        return id;
    }

    @GetMapping(path = "/basicData/provinceCity/{id}")
    public ProvinceCity getProvinceCity(@PathVariable Long id) {
        return service.findOne(ProvinceCity.class, id);
    }

    @GetMapping(path = "/basicData/provinceCity")
    public Page<ProvinceCity> listProvinceCity(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(ProvinceCity.class,page,size);
    }

}
