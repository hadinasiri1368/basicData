package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;

import org.basicData.dto.ParametersDto;
import org.basicData.model.ParamCategory;
import org.basicData.model.ParamType;
import org.basicData.model.Parameters;
import org.basicData.service.GenericService;
import org.basicData.service.ParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class ParametersAPI {
    @Autowired
    private GenericService<Parameters> service;
    @Autowired
    private ParametersService parametersService;

    @PostMapping(path = "/basicData/parameters/add")
    public Long addParameters(@RequestBody ParametersDto parametersDto, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        ParamType paramType = new ParamType();
        ParamCategory paramCategory = new ParamCategory();
        Parameters parameters = new Parameters();
        parameters.setId(parametersDto.getId());
        parameters.setParamName(parametersDto.getParamName());
        parameters.setParamCode(parametersDto.getParamCode());
        parameters.setValue(parametersDto.getValue());
        paramType.setId(parametersDto.getParamTypeId());
        parameters.setParamType(paramType);
        paramCategory.setId(parametersDto.getParamCategoryId());
        parameters.setParamCategory(paramCategory);
        parameters.setCompanyId(parametersDto.getCompanyId());
        service.insert(parameters, userId);
        return parameters.getId();
    }

    @PutMapping(path = "/basicData/parameters/edit")
    public Long editParameters(@RequestBody ParametersDto parametersDto, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        ParamType paramType = new ParamType();
        ParamCategory paramCategory = new ParamCategory();
        Parameters parameters = new Parameters();
        parameters.setId(parametersDto.getId());
        parameters.setParamName(parametersDto.getParamName());
        parameters.setParamCode(parametersDto.getParamCode());
        parameters.setValue(parametersDto.getValue());
        paramType.setId(parametersDto.getParamTypeId());
        parameters.setParamType(paramType);
        paramCategory.setId(parametersDto.getParamCategoryId());
        parameters.setParamCategory(paramCategory);
        parameters.setCompanyId(parametersDto.getCompanyId());
        service.update(parameters, userId, Parameters.class);
        return parameters.getId();
    }

    @DeleteMapping(path = "/basicData/parameters/remove/{id}")
    public Long removeParameters(@PathVariable Long id) {
        service.delete(id, Parameters.class);
        return id;
    }

    @GetMapping(path = "/basicData/parameters/{id}")
    public Parameters getParameters(@PathVariable Long id) {
        return service.findOne(Parameters.class, id);
    }

    @GetMapping(path = "/basicData/parameters")
    public Page<Parameters> listParameters(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(Parameters.class, page, size);
    }

    @GetMapping(path = "/basicData/paramCodeValue")
    public Parameters parametersValue(@RequestParam String paramCode, @RequestParam Long companyId) {
        return parametersService.findByCompanyAndCode(paramCode, companyId);
    }

}
