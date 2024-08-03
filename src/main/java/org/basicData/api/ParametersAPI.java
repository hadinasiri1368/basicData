package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;

import org.basicData.dto.ParametersDto;
import org.basicData.model.ParamCategory;
import org.basicData.model.ParamType;
import org.basicData.model.Parameters;
import org.basicData.service.ParametersService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class ParametersAPI {

    private final ParametersService parametersService;

    public ParametersAPI(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

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
        parametersService.insert(parameters, userId);
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
        parametersService.update(parameters, userId);
        return parameters.getId();
    }

    @DeleteMapping(path = "/basicData/parameters/remove/{id}")
    public Long removeParameters(@PathVariable Long id) {
        return (long) parametersService.delete(id);
    }

    @GetMapping(path = "/basicData/parameters/{id}")
    public Parameters getParameters(@PathVariable Long id) {
        return parametersService.findOne(Parameters.class, id);
    }

    @GetMapping(path = "/basicData/parameters")
    public Page<Parameters> listParameters(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return parametersService.findAll(Parameters.class, page, size);
    }

    @GetMapping(path = "/basicData/paramCodeValue")
    public Parameters parametersValue(@RequestParam String paramCode, @RequestParam Long companyId) {
        return parametersService.findByCompanyAndCode(paramCode, companyId);
    }

}
