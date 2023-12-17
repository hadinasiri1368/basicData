package org.basicData.api;

import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.ProvinceCity;
import org.basicData.model.RequestStatus;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestStatusAPI {
    @Autowired
    private GenericService<RequestStatus> service;

    @PostMapping(path = "/api/requestStatus/add")
    public Long addRequestStatus(@RequestBody RequestStatus requestStatus, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.insert(requestStatus, userId);
        return requestStatus.getId();
    }

    @PostMapping(path = "/api/requestStatus/edit")
    public Long editRequestStatus(@RequestBody RequestStatus requestStatus, HttpServletRequest request) {
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request));
        service.update(requestStatus, userId);
        return requestStatus.getId();
    }

    @PostMapping(path = "/api/provinceCity/remove/{id}")
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
