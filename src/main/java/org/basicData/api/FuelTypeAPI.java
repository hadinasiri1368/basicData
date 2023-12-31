package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.FuelType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class FuelTypeAPI {
    @Autowired
    private GenericService<FuelType> service;

    @PostMapping(path = "/api/fuelType/add")
    public Long addFuelType(@RequestBody FuelType fuelType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(fuelType, userId);
        return fuelType.getId();
    }

    @PostMapping(path = "/api/fuelType/edit")
    public Long editFuelType(@RequestBody FuelType fuelType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(fuelType, userId);
        return fuelType.getId();
    }

    @PostMapping(path = "/api/fuelType/remove/{id}")
    public Long removeFuelType(@PathVariable Long id) {
        service.delete(id,FuelType.class);
        return id;
    }

    @GetMapping(path = "/api/fuelType/{id}")
    public FuelType getFuelType(@PathVariable Long id) {
        return service.findOne(FuelType.class, id);
    }

    @GetMapping(path = "/api/fuelType")
    public List<FuelType> listFuelType() {
        return service.findAll(FuelType.class);
    }
}
