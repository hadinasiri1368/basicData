package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.dto.CarGroupDto;
import org.basicData.model.CarCapacity;
import org.basicData.model.CarGroup;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CarGroupAPI {
    @Autowired
    private GenericService<CarGroup> service;

    @PostMapping(path = "/api/carGroup/add")
    public Long addCarGroup(@RequestBody CarGroupDto carGroupDto, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        CarGroup carGroup = new CarGroup();
        carGroup.setId(carGroupDto.getId());
        carGroup.setName(carGroupDto.getName());
        CarCapacity carCapacity = new CarCapacity();
        carCapacity.setId(carGroupDto.getCarCapacityId());
        carGroup.setCarCapacity(carCapacity);
        service.insert(carGroup, userId);
        return carGroup.getId();
    }

    @PutMapping(path = "/api/carGroup/edit")
    public Long editCarGroup(@RequestBody CarGroupDto carGroupDto, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        CarGroup carGroup = new CarGroup();
        carGroup.setId(carGroupDto.getId());
        carGroup.setName(carGroupDto.getName());
        CarCapacity carCapacity = new CarCapacity();
        carCapacity.setId(carGroupDto.getCarCapacityId());
        carGroup.setCarCapacity(carCapacity);
        service.update(carGroup, userId, CarGroup.class);
        return carGroup.getId();
    }

    @DeleteMapping(path = "/api/carGroup/remove/{id}")
    public Long removeCarGroup(@PathVariable Long id) {
        service.delete(id, CarGroup.class);
        return id;
    }

    @GetMapping(path = "/api/carGroup/{id}")
    public CarGroup getCarGroup(@PathVariable Long id) {
        return service.findOne(CarGroup.class, id);
    }

    @GetMapping(path = "/api/carGroup")
    public Page<CarGroup> listCarGroup(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(CarGroup.class,page, size);
    }
}
