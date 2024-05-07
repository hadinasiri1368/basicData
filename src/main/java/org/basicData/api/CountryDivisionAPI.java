package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.dto.CountryDivisionDto;
import org.basicData.model.CountryDivision;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CountryDivisionAPI {
    @Autowired
    private GenericService<CountryDivision> service;

    @PostMapping(path = "/api/countryDivision/add")
    public Long addCountryDivision(@RequestBody CountryDivisionDto countryDivisionDto, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        CountryDivision countryDivision = new CountryDivision();
        countryDivision.setId(countryDivisionDto.getId());
        CountryDivision countryDivisionParent = new CountryDivision();
        countryDivisionParent.setId(countryDivisionDto.getParentId());
        countryDivision.setParent(countryDivisionParent);
        countryDivision.setCode(countryDivisionDto.getCode());
        countryDivision.setName(countryDivisionDto.getName());
        countryDivision.setIsFreeZone(countryDivisionDto.getIsFreeZone());
        countryDivision.setLevelToRoot(countryDivisionDto.getLevelToRoot());
        service.insert(countryDivision, userId);
        return countryDivision.getId();
    }

    @PutMapping(path = "/api/countryDivision/edit")
    public Long editCountryDivision(@RequestBody CountryDivisionDto countryDivisionDto, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        CountryDivision countryDivision = new CountryDivision();
        countryDivision.setId(countryDivisionDto.getId());
        CountryDivision countryDivisionParent = new CountryDivision();
        countryDivisionParent.setId(countryDivisionDto.getParentId());
        countryDivision.setParent(countryDivisionParent);
        countryDivision.setCode(countryDivisionDto.getCode());
        countryDivision.setName(countryDivisionDto.getName());
        countryDivision.setIsFreeZone(countryDivisionDto.getIsFreeZone());
        countryDivision.setLevelToRoot(countryDivisionDto.getLevelToRoot());
        service.update(countryDivision, userId, CountryDivision.class);
        return countryDivision.getId();
    }

    @DeleteMapping(path = "/api/countryDivision/remove/{id}")
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
