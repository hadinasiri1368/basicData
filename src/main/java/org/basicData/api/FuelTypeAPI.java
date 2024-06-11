package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.FuelType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class FuelTypeAPI {
    @Autowired
    private GenericService<FuelType> service;

    @PostMapping(path = "/basicData/fuelType/add")
    public Long addFuelType(@RequestBody FuelType fuelType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(fuelType, userId);
        return fuelType.getId();
    }

    @PutMapping(path = "/basicData/fuelType/edit")
    public Long editFuelType(@RequestBody FuelType fuelType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(fuelType, userId, FuelType.class);
        return fuelType.getId();
    }

    @DeleteMapping(path = "/basicData/fuelType/remove/{id}")
    public Long removeFuelType(@PathVariable Long id) {
        service.delete(id, FuelType.class);
        return id;
    }

    @GetMapping(path = "/basicData/fuelType/{id}")
    public FuelType getFuelType(@PathVariable Long id) {
        return service.findOne(FuelType.class, id);
    }

    @GetMapping(path = "/basicData/fuelType")
    public Page<FuelType> listFuelType(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(FuelType.class,page,size);
    }
}
