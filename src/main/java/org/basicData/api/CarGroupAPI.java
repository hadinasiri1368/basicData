package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.dto.CarGroupDto;
import org.basicData.model.CarCapacity;
import org.basicData.model.CarGroup;
import org.basicData.model.CarType;
import org.basicData.service.CarGroupService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CarGroupAPI {

    private final CarGroupService carGroupService;

    public CarGroupAPI(CarGroupService carGroupService) {
        this.carGroupService = carGroupService;
    }

    @PostMapping(path = "/basicData/carGroup/add")
    public Long addCarGroup(@RequestBody CarGroupDto carGroupDto, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        CarGroup carGroup = new CarGroup();
        carGroup.setId(carGroupDto.getId());
        CarCapacity carCapacity = new CarCapacity();
        carCapacity.setId(carGroupDto.getCarCapacityId());
        carGroup.setCarCapacityId(carCapacity.getId());
        CarType carType = new CarType();
        carType.setId(carGroupDto.getCarTypeId());
        carGroup.setCarTypeId(carType.getId());
        carGroup.setFactorValue(carGroupDto.getFactorValue());
        carGroup.setCompanyId(carGroupDto.getCompanyId());
        carGroupService.insert(carGroup, userId);
        return carGroup.getId();
    }

    @PutMapping(path = "/basicData/carGroup/edit")
    public Long editCarGroup(@RequestBody CarGroupDto carGroupDto, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        CarGroup carGroup = new CarGroup();
        carGroup.setId(carGroupDto.getId());
        CarCapacity carCapacity = new CarCapacity();
        carCapacity.setId(carGroupDto.getCarCapacityId());
        carGroup.setCarCapacityId(carCapacity.getId());
        CarType carType = new CarType();
        carType.setId(carGroupDto.getCarTypeId());
        carGroup.setCarTypeId(carType.getId());
        carGroup.setFactorValue(carGroupDto.getFactorValue());
        carGroup.setCompanyId(carGroupDto.getCompanyId());
        carGroupService.update(carGroup, userId);
        return carGroup.getId();
    }

    @DeleteMapping(path = "/basicData/carGroup/remove/{id}")
    public Long removeCarGroup(@PathVariable Long id) {
        return (long) carGroupService.delete(id);
    }

    @GetMapping(path = "/basicData/carGroup/{id}")
    public CarGroup getCarGroup(@PathVariable Long id) {
        return carGroupService.findOne(CarGroup.class, id);
    }

    @GetMapping(path = "/basicData/carGroup")
    public Page<CarGroup> listCarGroup(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return carGroupService.findAll(CarGroup.class, page, size);
    }

    @GetMapping(path = "/basicData/carGroupValue")
    public CarGroup carGroupValue(@RequestParam Long carTypeId, @RequestParam Long carCapacityId, @RequestParam Long companyId) {
        return carGroupService.findByCompanyAndCode(carTypeId, carCapacityId, companyId);
    }

}
