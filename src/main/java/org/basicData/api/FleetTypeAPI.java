package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.FleetType;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class FleetTypeAPI {
    @Autowired
    private GenericService<FleetType> service;

    @PostMapping(path = "/basicData/fleetType/add")
    public Long addFleetType(@RequestBody FleetType fleetType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(fleetType, userId);
        return fleetType.getId();
    }

    @PutMapping(path = "/basicData/fleetType/edit")
    public Long editFleetType(@RequestBody FleetType fleetType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        Long id = fleetType.getId();
        service.update(fleetType, userId, FleetType.class);
        return id;
    }

    @DeleteMapping(path = "/basicData/fleetType/remove/{id}")
    public Long removeFleetType(@PathVariable Long id) {
        service.delete(id, FleetType.class);
        return id;
    }

    @GetMapping(path = "/basicData/fleetType/{id}")
    public FleetType getFleetType(@PathVariable Long id) {
        return service.findOne(FleetType.class, id);
    }

    @GetMapping(path = "/basicData/fleetType")
    public Page<FleetType> listFleetType(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(FleetType.class,page,size);
    }
}
