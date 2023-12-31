package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.CarCapacity;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CarCapacityAPI {
    @Autowired
    private GenericService<CarCapacity> service;

    @PostMapping(path = "/api/carCapacity/add")
    public Long addCarCapacity(@RequestBody CarCapacity carCapacity, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(carCapacity, userId);
        return carCapacity.getId();
    }

    @PostMapping(path = "/api/carCapacity/edit")
    public Long editCarCapacity(@RequestBody CarCapacity carCapacity, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(carCapacity, userId);
        return carCapacity.getId();
    }

    @PostMapping(path = "/api/carCapacity/remove/{id}")
    public Long removeCarCapacity(@PathVariable Long id) {
        service.delete(id, CarCapacity.class);
        return id;
    }

    @GetMapping(path = "/api/carCapacity/{id}")
    public CarCapacity getCarCapacity(@PathVariable Long id) {
        return service.findOne(CarCapacity.class, id);
    }

    @GetMapping(path = "/api/carCapacity")
    public List<CarCapacity> listCarCapacity() {
        return service.findAll(CarCapacity.class);
    }
}
