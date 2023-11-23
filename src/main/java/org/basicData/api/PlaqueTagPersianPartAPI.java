package org.basicData.api;

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
    public Long addPerson(@RequestBody PlaqueTagPersianPart plaqueTagPersianPart) {
        Long userId = CommonUtils.getUserId(null);
        service.insert(plaqueTagPersianPart, userId);
        return plaqueTagPersianPart.getId();
    }

    @PostMapping(path = "/api/plaqueTagPersianPart/edit")
    public Long editPerson(@RequestBody PlaqueTagPersianPart plaqueTagPersianPart) {
        Long userId = CommonUtils.getUserId(null);
        service.update(plaqueTagPersianPart, userId);
        return plaqueTagPersianPart.getId();
    }

    @PostMapping(path = "/api/plaqueTagPersianPart/remove/{id}")
    public Long removePerson(@PathVariable Long id) {
        service.delete(new PlaqueTagPersianPart(id, null));
        return id;
    }

    @GetMapping(path = "/api/plaqueTagPersianPart/{id}")
    public PlaqueTagPersianPart getPerson(@PathVariable Long id) {
        return service.findOne(PlaqueTagPersianPart.class, id);
    }

    @GetMapping(path = "/api/plaqueTagPersianPart")
    public List<PlaqueTagPersianPart> listPerson() {
        return service.findAll(PlaqueTagPersianPart.class);
    }
}
