package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.CarProperty;
import org.basicData.service.AuthenticationServiceProxy;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CarPropertyAPI {
    @Autowired
    private GenericService<CarProperty> service;
    @Autowired
    AuthenticationServiceProxy authenticationServiceProxy;

    @PostMapping(path = "/api/carProperty/add")
    public Long addCarProperty(@RequestBody CarProperty carProperty, HttpServletRequest request) throws Exception {
        Long userId = CommonUtils.longValue(authenticationServiceProxy.getUser(CommonUtils.getToken(request)));
        service.insert(carProperty, userId);
        return carProperty.getId();
    }

    @PutMapping(path = "/api/carProperty/edit")
    public Long editCarProperty(@RequestBody CarProperty carProperty, HttpServletRequest request) throws Exception {
        Long userId = CommonUtils.longValue(authenticationServiceProxy.getUser(CommonUtils.getToken(request)));
        service.update(carProperty, userId , CarProperty.class);
        return carProperty.getId();
    }

    @DeleteMapping(path = "/api/carProperty/remove/{id}")
    public Long removeCarProperty(@PathVariable Long id) {
        service.delete(id, CarProperty.class);
        return id;
    }

    @GetMapping(path = "/api/carProperty/{id}")
    public CarProperty getCarProperty(@PathVariable Long id) {
        return service.findOne(CarProperty.class, id);
    }

    @GetMapping(path = "/api/carProperty")
    public List<CarProperty> listCarProperty() {
        return service.findAll(CarProperty.class);
    }

}
