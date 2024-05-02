package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.FuelType;
import org.basicData.service.AuthenticationServiceProxy;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class FuelTypeAPI {
    @Autowired
    private GenericService<FuelType> service;
    @Autowired
    private AuthenticationServiceProxy authenticationServiceProxy;

    @PostMapping(path = "/api/fuelType/add")
    public Long addFuelType(@RequestBody FuelType fuelType, HttpServletRequest request) throws Exception {
        Long userId = CommonUtils.longValue(authenticationServiceProxy.getUser(CommonUtils.getToken(request)));
        service.insert(fuelType, userId);
        return fuelType.getId();
    }

    @PutMapping(path = "/api/fuelType/edit")
    public Long editFuelType(@RequestBody FuelType fuelType, HttpServletRequest request) throws Exception {
        Long userId = CommonUtils.longValue(authenticationServiceProxy.getUser(CommonUtils.getToken(request)));
        service.update(fuelType, userId, FuelType.class);
        return fuelType.getId();
    }

    @DeleteMapping(path = "/api/fuelType/remove/{id}")
    public Long removeFuelType(@PathVariable Long id) {
        service.delete(id, FuelType.class);
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
