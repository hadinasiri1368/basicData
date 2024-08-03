package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.CarType;
import org.basicData.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CarTypeAPI {
    private final GenericService<CarType> service;

    public CarTypeAPI(GenericService<CarType> service) {
        this.service = service;
    }

    @PostMapping(path = "/basicData/carType/add")
    public Long addCarType(@RequestBody CarType carType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(carType, userId);
        return carType.getId();
    }

    @PutMapping(path = "/basicData/carType/edit")
    public Long editCarType(@RequestBody CarType carType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(carType, userId, CarType.class);
        return carType.getId();
    }

    @DeleteMapping(path = "/basicData/carType/remove/{id}")
    public Long removeCarType(@PathVariable Long id) {
        return (long) service.delete(id, CarType.class);
    }

    @GetMapping(path = "/basicData/carType/{id}")
    public CarType getCarType(@PathVariable Long id) {
        return service.findOne(CarType.class, id);
    }

    @GetMapping(path = "/basicData/carType")
    public Page<CarType> listCarType(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(CarType.class,page,size);
    }

}
