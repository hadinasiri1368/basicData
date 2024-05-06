package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.RequestStatus;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class RequestStatusAPI {
    @Autowired
    private GenericService<RequestStatus> service;

    @PostMapping(path = "/api/requestStatus/add")
    public Long addRequestStatus(@RequestBody RequestStatus requestStatus, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
Long userId = CommonUtils.getUserId(CommonUtils.getToken(request),uuid);
        service.insert(requestStatus, userId);
        return requestStatus.getId();
    }

    @PutMapping(path = "/api/requestStatus/edit")
    public Long editRequestStatus(@RequestBody RequestStatus requestStatus, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
Long userId = CommonUtils.getUserId(CommonUtils.getToken(request),uuid);
        service.update(requestStatus, userId, RequestStatus.class);
        return requestStatus.getId();
    }

    @DeleteMapping(path = "/api/requestStatus/remove/{id}")
    public Long removeRequestStatus(@PathVariable Long id) {
        service.delete(id, RequestStatus.class);
        return id;
    }

    @GetMapping(path = "/api/requestStatus/{id}")
    public RequestStatus getRequestStatus(@PathVariable Long id) {
        return service.findOne(RequestStatus.class, id);
    }

    @GetMapping(path = "/api/requestStatus")
    public List<RequestStatus> listRequestStatus() {
        return service.findAll(RequestStatus.class);
    }

}

