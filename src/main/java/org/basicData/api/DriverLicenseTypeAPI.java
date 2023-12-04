package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.DriverLicenseType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverLicenseTypeAPI {
    @Autowired
    private GenericService<DriverLicenseType> service;

    @PostMapping(path = "/api/driverLicenseType/add")
    public Long addPerson(@RequestBody DriverLicenseType driverLicenseType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(driverLicenseType, userId);
        return driverLicenseType.getId();
    }

    @PostMapping(path = "/api/driverLicenseType/edit")
    public Long editPerson(@RequestBody DriverLicenseType driverLicenseType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(driverLicenseType, userId);
        return driverLicenseType.getId();
    }

    @PostMapping(path = "/api/driverLicenseType/remove/{id}")
    public Long removePerson(@PathVariable Long id) {
        service.delete(new DriverLicenseType(id, null));
        return id;
    }

    @GetMapping(path = "/api/driverLicenseType/{id}")
    public DriverLicenseType getPerson(@PathVariable Long id) {
        return service.findOne(DriverLicenseType.class, id);
    }

    @GetMapping(path = "/api/driverLicenseType")
    public List<DriverLicenseType> listPerson() {
        return service.findAll(DriverLicenseType.class);
    }
}
