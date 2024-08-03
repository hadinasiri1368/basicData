package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.ParamCategory;
import org.basicData.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class ParamCategoryAPI {
    private final GenericService<ParamCategory> service;

    public ParamCategoryAPI(GenericService<ParamCategory> service) {
        this.service = service;
    }

    @PostMapping(path = "/basicData/paramCategory/add")
    public Long addParamCategory(@RequestBody ParamCategory paramCategory, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(paramCategory, userId);
        return paramCategory.getId();
    }

    @PutMapping(path = "/basicData/paramCategory/edit")
    public Long editParamCategory(@RequestBody ParamCategory paramCategory, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(paramCategory, userId, ParamCategory.class);
        return paramCategory.getId();
    }

    @DeleteMapping(path = "/basicData/paramCategory/remove/{id}")
    public Long removeParamCategory(@PathVariable Long id) {
        return (long) service.delete(id, ParamCategory.class);
    }

    @GetMapping(path = "/basicData/paramCategory/{id}")
    public ParamCategory getParamCategory(@PathVariable Long id) {
        return service.findOne(ParamCategory.class, id);
    }

    @GetMapping(path = "/basicData/paramCategory")
    public Page<ParamCategory> listParamCategory(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(ParamCategory.class, page, size);
    }

}
