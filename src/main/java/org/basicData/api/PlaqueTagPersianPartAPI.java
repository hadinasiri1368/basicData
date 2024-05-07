package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.PlaqueTagPersianPart;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class PlaqueTagPersianPartAPI {
    @Autowired
    private GenericService<PlaqueTagPersianPart> service;

    @PostMapping(path = "/api/plaqueTagPersianPart/add")
    public Long addPlaqueTagPersianPart(@RequestBody PlaqueTagPersianPart plaqueTagPersianPart, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(plaqueTagPersianPart, userId);
        return plaqueTagPersianPart.getId();
    }

    @PutMapping(path = "/api/plaqueTagPersianPart/edit")
    public Long editPlaqueTagPersianPart(@RequestBody PlaqueTagPersianPart plaqueTagPersianPart, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(plaqueTagPersianPart, userId, PlaqueTagPersianPart.class);
        return plaqueTagPersianPart.getId();
    }

    @DeleteMapping(path = "/api/plaqueTagPersianPart/remove/{id}")
    public Long removePlaqueTagPersianPart(@PathVariable Long id) {
        service.delete(id, PlaqueTagPersianPart.class);
        return id;
    }

    @GetMapping(path = "/api/plaqueTagPersianPart/{id}")
    public PlaqueTagPersianPart getPlaqueTagPersianPart(@PathVariable Long id) {
        return service.findOne(PlaqueTagPersianPart.class, id);
    }

    @GetMapping(path = "/api/plaqueTagPersianPart")
    public Page<PlaqueTagPersianPart> listPlaqueTagPersianPart(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(PlaqueTagPersianPart.class,page,size);
    }
}
