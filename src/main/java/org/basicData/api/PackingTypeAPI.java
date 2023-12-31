package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.PackingType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class PackingTypeAPI {
    @Autowired
    private GenericService<PackingType> service;

    @PostMapping(path = "/api/packingType/add")
    public Long addPackingType(@RequestBody PackingType packingType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(packingType, userId);
        return packingType.getId();
    }

    @PostMapping(path = "/api/packingType/edit")
    public Long editPackingType(@RequestBody PackingType packingType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(packingType, userId);
        return packingType.getId();
    }

    @PostMapping(path = "/api/packingType/remove/{id}")
    public Long removePackingType(@PathVariable Long id) {
        service.delete(id, PackingType.class);
        return id;
    }

    @GetMapping(path = "/api/packingType/{id}")
    public PackingType getPackingType(@PathVariable Long id) {
        return service.findOne(PackingType.class, id);
    }

    @GetMapping(path = "/api/packingType")
    public List<PackingType> listPackingType() {
        return service.findAll(PackingType.class);
    }

}
