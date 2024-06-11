package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.PackingType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class PackingTypeAPI {
    @Autowired
    private GenericService<PackingType> service;

    @PostMapping(path = "/basicData/packingType/add")
    public Long addPackingType(@RequestBody PackingType packingType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(packingType, userId);
        return packingType.getId();
    }

    @PutMapping(path = "/basicData/packingType/edit")
    public Long editPackingType(@RequestBody PackingType packingType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(packingType, userId, PackingType.class);
        return packingType.getId();
    }

    @DeleteMapping(path = "/basicData/packingType/remove/{id}")
    public Long removePackingType(@PathVariable Long id) {
        service.delete(id, PackingType.class);
        return id;
    }

    @GetMapping(path = "/basicData/packingType/{id}")
    public PackingType getPackingType(@PathVariable Long id) {
        return service.findOne(PackingType.class, id);
    }

    @GetMapping(path = "/basicData/packingType")
    public Page<PackingType> listPackingType(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(PackingType.class, page, size);
    }

}
