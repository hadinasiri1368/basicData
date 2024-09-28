package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.dto.LoadingTypeCompanyDto;
import org.basicData.model.LoadingTypeCompany;
import org.basicData.service.LoadingTypeCompanyService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class LoadingTypeCompanyAPI {
    private final LoadingTypeCompanyService loadingTypeCompanyService;

    public LoadingTypeCompanyAPI(LoadingTypeCompanyService loadingTypeCompanyService) {
        this.loadingTypeCompanyService = loadingTypeCompanyService;
    }

    @PostMapping(path = "/basicData/loadingTypeCompany/add")
    public Long addLoadingTypeCompany(@RequestBody LoadingTypeCompany loadingTypeCompany, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        loadingTypeCompanyService.insert(loadingTypeCompany, userId);
        return loadingTypeCompany.getId();
    }

    @PutMapping(path = "/basicData/loadingTypeCompany/edit")
    public Long editLoadingType(@RequestBody LoadingTypeCompany loadingTypeCompany, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        loadingTypeCompanyService.update(loadingTypeCompany, userId);
        return loadingTypeCompany.getId();
    }

    @DeleteMapping(path = "/basicData/loadingTypeCompany/remove/{id}")
    public Long removeLoadingType(@PathVariable Long id) {
        return (long) loadingTypeCompanyService.delete(id);
    }

    @GetMapping(path = "/basicData/loadingTypeCompany/{id}")
    public LoadingTypeCompany getLoadingType(@PathVariable Long id) {
        return loadingTypeCompanyService.findOne(LoadingTypeCompany.class, id);
    }

    @GetMapping(path = "/basicData/loadingTypeCompany")
    public Page<LoadingTypeCompany> listLoadingType(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return loadingTypeCompanyService.findAll(LoadingTypeCompany.class, page, size);
    }

    @GetMapping(path = "/basicData/loadingTypeCompanyValue")
    public LoadingTypeCompany listLoadingTypeValue(@RequestParam Long loadingTypeId, @RequestParam Long companyId,HttpServletRequest request) {
        String uuid = request.getHeader("X-UUID");
        String token = CommonUtils.getToken(request);
        return loadingTypeCompanyService.findByCompanyAndCode(loadingTypeId, companyId, uuid , token);
    }

    @GetMapping(path = "/basicData/loadingTypeCompanyData")
    public Page<LoadingTypeCompanyDto> listLoadingTypeCompanyData(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, HttpServletRequest request) {
        String token = CommonUtils.getToken(request);
        String uuid = request.getHeader("X-UUID");
        return loadingTypeCompanyService.findAll(token, uuid, page, size);
    }
}
