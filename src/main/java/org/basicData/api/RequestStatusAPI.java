package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.RequestStatus;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class RequestStatusAPI {
    @Autowired
    private GenericService<RequestStatus> service;

    @PostMapping(path = "/basicData/requestStatus/add")
    public Long addRequestStatus(@RequestBody RequestStatus requestStatus, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(requestStatus, userId);
        return requestStatus.getId();
    }

    @PutMapping(path = "/basicData/requestStatus/edit")
    public Long editRequestStatus(@RequestBody RequestStatus requestStatus, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(requestStatus, userId, RequestStatus.class);
        return requestStatus.getId();
    }

    @DeleteMapping(path = "/basicData/requestStatus/remove/{id}")
    public Long removeRequestStatus(@PathVariable Long id) {
        service.delete(id, RequestStatus.class);
        return id;
    }

    @GetMapping(path = "/basicData/requestStatus/{id}")
    public RequestStatus getRequestStatus(@PathVariable Long id) {
        return service.findOne(RequestStatus.class, id);
    }

    @GetMapping(path = "/basicData/requestStatus")
    public Page<RequestStatus> listRequestStatus(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        return service.findAll(RequestStatus.class,page,size);
    }

}

