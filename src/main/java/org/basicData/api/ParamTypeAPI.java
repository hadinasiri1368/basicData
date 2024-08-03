package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.ParamType;
import org.basicData.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class ParamTypeAPI {
    private final GenericService<ParamType> service;

    public ParamTypeAPI(GenericService<ParamType> service) {
        this.service = service;
    }

    @PostMapping(path = "/basicData/paramType/add")
    public Long addParamType(@RequestBody ParamType paramType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(paramType, userId);
        return paramType.getId();
    }

    @PutMapping(path = "/basicData/paramType/edit")
    public Long editParamType(@RequestBody ParamType paramType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(paramType, userId, ParamType.class);
        return paramType.getId();
    }

    @DeleteMapping(path = "/basicData/paramType/remove/{id}")
    public Long removeParamType(@PathVariable Long id) {
        return (long) service.delete(id, ParamType.class);
    }

    @GetMapping(path = "/basicData/paramType/{id}")
    public ParamType getParamType(@PathVariable Long id) {
        return service.findOne(ParamType.class, id);
    }

    @GetMapping(path = "/basicData/paramType")
    public Page<ParamType> listParamType(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(ParamType.class, page, size);
    }

}
