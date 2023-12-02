package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.FleetType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FleetTypeAPI {
    @Autowired
    private GenericService<FleetType> service;

    @PostMapping(path = "/api/fleetType/add")
    public Long addPerson(@RequestBody FleetType fleetType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(fleetType, userId);
        return fleetType.getId();
    }

    @PostMapping(path = "/api/fleetType/edit")
    public Long editPerson(@RequestBody FleetType fleetType, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(fleetType, userId);
        return fleetType.getId();
    }

    @PostMapping(path = "/api/fleetType/remove/{id}")
    public Long removePerson(@PathVariable Long id) {
        service.delete(new FleetType(id, null));
        return id;
    }

    @GetMapping(path = "/api/fleetType/{id}")
    public FleetType getPerson(@PathVariable Long id) {
        return service.findOne(FleetType.class, id);
    }

    @GetMapping(path = "/api/fleetType")
    public List<FleetType> listPerson() {
        return service.findAll(FleetType.class);
    }
}
