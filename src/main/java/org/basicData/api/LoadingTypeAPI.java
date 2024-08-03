package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;

import org.basicData.model.LoadingType;
import org.basicData.service.LoadingTypeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class LoadingTypeAPI {

    private final LoadingTypeService loadingTypeService;

    public LoadingTypeAPI(LoadingTypeService loadingTypeService) {
        this.loadingTypeService = loadingTypeService;
    }

    @PostMapping(path = "/basicData/loadingType/add")
    public Long addLoadingType(@RequestBody LoadingType loadingType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        loadingTypeService.insert(loadingType, userId);
        return loadingType.getId();
    }

    @PutMapping(path = "/basicData/loadingType/edit")
    public Long editLoadingType(@RequestBody LoadingType loadingType, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        loadingTypeService.update(loadingType, userId);
        return loadingType.getId();
    }

    @DeleteMapping(path = "/basicData/loadingType/remove/{id}")
    public Long removeLoadingType(@PathVariable Long id) {
        return (long) loadingTypeService.delete(id);
    }

    @GetMapping(path = "/basicData/loadingType/{id}")
    public LoadingType getLoadingType(@PathVariable Long id) {
        return loadingTypeService.findOne(LoadingType.class, id);
    }

    @GetMapping(path = "/basicData/loadingType")
    public Page<LoadingType> listLoadingType(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return loadingTypeService.findAll(LoadingType.class, page, size);
    }

    @GetMapping(path = "/basicData/loadingTypeValue")
    public LoadingType listLoadingTypeValue(@RequestParam Long code, @RequestParam Long companyId) {
        return loadingTypeService.findByCompanyAndCode(code, companyId);
    }

}
