package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.CarProperty;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CarPropertyAPI {
    @Autowired
    private GenericService<CarProperty> service;

    @PostMapping(path = "/basicData/carProperty/add")
    public Long addCarProperty(@RequestBody CarProperty carProperty, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(carProperty, userId);
        return carProperty.getId();
    }

    @PutMapping(path = "/basicData/carProperty/edit")
    public Long editCarProperty(@RequestBody CarProperty carProperty, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(carProperty, userId, CarProperty.class);
        return carProperty.getId();
    }

    @DeleteMapping(path = "/basicData/carProperty/remove/{id}")
    public Long removeCarProperty(@PathVariable Long id) {
        service.delete(id, CarProperty.class);
        return id;
    }

    @GetMapping(path = "/basicData/carProperty/{id}")
    public CarProperty getCarProperty(@PathVariable Long id) {
        return service.findOne(CarProperty.class, id);
    }

    @GetMapping(path = "/basicData/carProperty")
    public Page<CarProperty> listCarProperty(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(CarProperty.class,page,size);
    }

}
