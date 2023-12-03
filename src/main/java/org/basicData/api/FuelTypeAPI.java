package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.FuelType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FuelTypeAPI {
    @Autowired
    private GenericService<FuelType> service;

    @PostMapping(path = "/api/fuelType/add")
    public Long addPerson(@RequestBody FuelType fuelType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(fuelType, userId);
        return fuelType.getId();
    }

    @PostMapping(path = "/api/fuelType/edit")
    public Long editPerson(@RequestBody FuelType fuelType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(fuelType, userId);
        return fuelType.getId();
    }

    @PostMapping(path = "/api/fuelType/remove/{id}")
    public Long removePerson(@PathVariable Long id) {
        service.delete(new FuelType(id, null));
        return id;
    }

    @GetMapping(path = "/api/fuelType/{id}")
    public FuelType getPerson(@PathVariable Long id) {
        return service.findOne(FuelType.class, id);
    }

    @GetMapping(path = "/api/fuelType")
    public List<FuelType> listPerson() {
        return service.findAll(FuelType.class);
    }
}
