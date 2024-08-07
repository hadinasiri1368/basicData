package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.CarCapacity;
import org.basicData.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CarCapacityAPI {
    private final GenericService<CarCapacity> service;

    public CarCapacityAPI(GenericService<CarCapacity> service) {
        this.service = service;
    }

    @PostMapping(path = "/basicData/carCapacity/add")
    public Long addCarCapacity(@RequestBody CarCapacity carCapacity, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(carCapacity, userId);
        return carCapacity.getId();
    }

    @PutMapping(path = "/basicData/carCapacity/edit")
    public Long editCarCapacity(@RequestBody CarCapacity carCapacity, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(carCapacity, userId, CarCapacity.class);
        return carCapacity.getId();
    }

    @DeleteMapping(path = "/basicData/carCapacity/remove/{id}")
    public Long removeCarCapacity(@PathVariable Long id) {
        return (long) service.delete(id, CarCapacity.class);
    }

    @GetMapping(path = "/basicData/carCapacity/{id}")
    public CarCapacity getCarCapacity(@PathVariable Long id) {
        return service.findOne(CarCapacity.class, id);
    }

    @GetMapping(path = "/basicData/carCapacity")
    public Page<CarCapacity> listCarCapacity(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(CarCapacity.class,page, size);
    }
}
