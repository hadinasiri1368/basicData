package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.DriverLicenseType;
import org.basicData.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class DriverLicenseTypeAPI {
    private final GenericService<DriverLicenseType> service;

    public DriverLicenseTypeAPI(GenericService<DriverLicenseType> service) {
        this.service = service;
    }

    @PostMapping(path = "/basicData/driverLicenseType/add")
    public Long addDriverLicenseType(@RequestBody DriverLicenseType driverLicenseType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(driverLicenseType, userId);
        return driverLicenseType.getId();
    }

    @PutMapping(path = "/basicData/driverLicenseType/edit")
    public Long editDriverLicenseType(@RequestBody DriverLicenseType driverLicenseType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(driverLicenseType, userId, DriverLicenseType.class);
        return driverLicenseType.getId();
    }

    @DeleteMapping(path = "/basicData/driverLicenseType/remove/{id}")
    public Long removeDriverLicenseType(@PathVariable Long id) {
        return (long) service.delete(id, DriverLicenseType.class);
    }

    @GetMapping(path = "/basicData/driverLicenseType/{id}")
    public DriverLicenseType getDriverLicenseType(@PathVariable Long id) {
        return service.findOne(DriverLicenseType.class, id);
    }

    @GetMapping(path = "/basicData/driverLicenseType")
    public Page<DriverLicenseType> listDriverLicenseType(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(DriverLicenseType.class,page,size);
    }
}
