package org.basicData.api;

import org.basicData.common.CommonUtils;
import org.basicData.model.CarCapacity;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
//
@RestController
public class CarCapacityAPI {
    @Autowired
    private GenericService<CarCapacity> service;

    @PostMapping(path = "/api/carCapacity/add")
    public Long addPerson(@RequestBody CarCapacity carCapacity) {
        Long userId = CommonUtils.getUserId(null);
        service.insert(carCapacity, userId);
        return carCapacity.getId();
    }

    @PostMapping(path = "/api/carCapacity/edit")
    public Long editPerson(@RequestBody CarCapacity carCapacity) {
        Long userId = CommonUtils.getUserId(null);
        service.update(carCapacity, userId);
        return carCapacity.getId();
    }

    @PostMapping(path = "/api/carCapacity/remove/{id}")
    public Long removePerson(@PathVariable Long id) {
        service.delete(new CarCapacity(id, null, null));
        return id;
    }

    @GetMapping(path = "/api/carCapacity/{id}")
    public CarCapacity getPerson(@PathVariable Long id) {
        return service.findOne(CarCapacity.class, id);
    }

    @GetMapping(path = "/api/carCapacity")
    public List<CarCapacity> listPerson() {
        return service.findAll(CarCapacity.class);
    }
}
