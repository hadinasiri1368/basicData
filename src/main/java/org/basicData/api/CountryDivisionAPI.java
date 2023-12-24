package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.CountryDivision;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryDivisionAPI {
    @Autowired
    private GenericService<CountryDivision> service;

    @PostMapping(path = "/api/countryDivision/add")
    public Long addCountryDivision(@RequestBody CountryDivision countryDivision, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(countryDivision, userId);
        return countryDivision.getId();
    }

    @PostMapping(path = "/api/countryDivision/edit")
    public Long editCountryDivision(@RequestBody CountryDivision countryDivision, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(countryDivision, userId);
        return countryDivision.getId();
    }

    @PostMapping(path = "/api/countryDivision/remove/{id}")
    public Long removeCountryDivision(@PathVariable Long id) {
        service.delete(id, CountryDivision.class);
        return id;
    }

    @GetMapping(path = "/api/countryDivision/{id}")
    public CountryDivision getCountryDivision(@PathVariable Long id) {
        return service.findOne(CountryDivision.class, id);
    }

    @GetMapping(path = "/api/countryDivision")
    public List<CountryDivision> listCountryDivision() {
        return service.findAll(CountryDivision.class);
    }

}
