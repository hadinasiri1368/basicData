package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.PlaqueTagPersianPart;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaqueTagPersianPartAPI {
    @Autowired
    private GenericService<PlaqueTagPersianPart> service;

    @PostMapping(path = "/api/plaqueTagPersianPart/add")
    public Long addPlaqueTagPersianPart(@RequestBody PlaqueTagPersianPart plaqueTagPersianPart, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(plaqueTagPersianPart, userId);
        return plaqueTagPersianPart.getId();
    }

    @PostMapping(path = "/api/plaqueTagPersianPart/edit")
    public Long editPlaqueTagPersianPart(@RequestBody PlaqueTagPersianPart plaqueTagPersianPart, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(plaqueTagPersianPart, userId);
        return plaqueTagPersianPart.getId();
    }

    @PostMapping(path = "/api/plaqueTagPersianPart/remove/{id}")
    public Long removePlaqueTagPersianPart(@PathVariable Long id) {
        service.delete(id,PlaqueTagPersianPart.class);
        return id;
    }

    @GetMapping(path = "/api/plaqueTagPersianPart/{id}")
    public PlaqueTagPersianPart getPlaqueTagPersianPart(@PathVariable Long id) {
        return service.findOne(PlaqueTagPersianPart.class, id);
    }

    @GetMapping(path = "/api/plaqueTagPersianPart")
    public List<PlaqueTagPersianPart> listPlaqueTagPersianPart() {
        return service.findAll(PlaqueTagPersianPart.class);
    }
}
