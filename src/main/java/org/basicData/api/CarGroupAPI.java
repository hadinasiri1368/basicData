package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.dto.CarGroupDto;
import org.basicData.model.CarCapacity;
import org.basicData.model.CarGroup;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarGroupAPI {
    @Autowired
    private GenericService<CarGroup> service;

    @PostMapping(path = "/api/carGroup/add")
    public Long addCarGroup(@RequestBody CarGroupDto carGroupDto, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        CarGroup carGroup = new CarGroup();
        carGroup.setId(carGroupDto.getId());
        carGroup.setName(carGroupDto.getName());
        CarCapacity carCapacity = new CarCapacity();
        carCapacity.setId(carGroupDto.getCarCapacityId());
        carGroup.setCarCapacity(carCapacity);
        service.insert(carGroup, userId);
        return carGroup.getId();
    }

    @PostMapping(path = "/api/carGroup/edit")
    public Long editCarGroup(@RequestBody CarGroup carGroup, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(carGroup, userId);
        return carGroup.getId();
    }

    @PostMapping(path = "/api/carGroup/remove/{id}")
    public Long removeCarGroup(@PathVariable Long id) {
        service.delete(id,CarGroup.class);
        return id;
    }

    @GetMapping(path = "/api/carGroup/{id}")
    public CarGroup getCarGroup(@PathVariable Long id) {
        return service.findOne(CarGroup.class, id);
    }

    @GetMapping(path = "/api/carGroup")
    public List<CarGroup> listCarGroup() {
        return service.findAll(CarGroup.class);
    }
}
