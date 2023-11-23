package org.basicData.api;

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
    public Long addPerson(@RequestBody CarGroupDto carGroupDto) {
        Long userId = CommonUtils.getUserId(null);
        CarGroup carGroup =new CarGroup();
        carGroup.setId(carGroupDto.getId());
        carGroup.setName(carGroupDto.getName());
        CarCapacity carCapacity=new CarCapacity();
        carCapacity.setId(carGroupDto.getF_car_capacity_id());
        carGroup.setCarCapacity(carCapacity);
        service.insert(carGroup, userId);
        return carGroup.getId();
    }

    @PostMapping(path = "/api/carGroup/edit")
    public Long editPerson(@RequestBody CarGroup carGroup) {
        Long userId = CommonUtils.getUserId(null);
        service.update(carGroup, userId);
        return carGroup.getId();
    }

    @PostMapping(path = "/api/carGroup/remove/{id}")
    public Long removePerson(@PathVariable Long id) {
        service.delete(new CarGroup(id, null, null));
        return id;
    }

    @GetMapping(path = "/api/carGroup/{id}")
    public CarGroup getPerson(@PathVariable Long id) {
        return service.findOne(CarGroup.class, id);
    }

    @GetMapping(path = "/api/carGroup")
    public List<CarGroup> listPerson() {
        return service.findAll(CarGroup.class);
    }
}
