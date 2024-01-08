package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.DriverLicenseType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class DriverLicenseTypeAPI {
    @Autowired
    private GenericService<DriverLicenseType> service;

    @PostMapping(path = "/api/driverLicenseType/add")
    public Long addDriverLicenseType(@RequestBody DriverLicenseType driverLicenseType, HttpServletRequest request) throws Exception {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(driverLicenseType, userId);
        return driverLicenseType.getId();
    }

    @PostMapping(path = "/api/driverLicenseType/edit")
    public Long editDriverLicenseType(@RequestBody DriverLicenseType driverLicenseType, HttpServletRequest request) throws Exception {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(driverLicenseType, userId , DriverLicenseType.class);
        return driverLicenseType.getId();
    }

    @PostMapping(path = "/api/driverLicenseType/remove/{id}")
    public Long removeDriverLicenseType(@PathVariable Long id) {
        service.delete(id, DriverLicenseType.class);
        return id;
    }

    @GetMapping(path = "/api/driverLicenseType/{id}")
    public DriverLicenseType getDriverLicenseType(@PathVariable Long id) {
        return service.findOne(DriverLicenseType.class, id);
    }

    @GetMapping(path = "/api/driverLicenseType")
    public List<DriverLicenseType> listDriverLicenseType() {
        return service.findAll(DriverLicenseType.class);
    }
}
