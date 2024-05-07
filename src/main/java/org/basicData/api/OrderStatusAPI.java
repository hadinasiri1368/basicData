package org.basicData.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.basicData.common.CommonUtils;
import org.basicData.model.OrderStatus;
import org.basicData.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class OrderStatusAPI {
    @Autowired
    private GenericService<OrderStatus> service;

    @PostMapping(path = "/api/orderStatus/add")
    public Long addOrderStatus(@RequestBody OrderStatus orderStatus, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.insert(orderStatus, userId);
        return orderStatus.getId();
    }

    @PutMapping(path = "/api/orderStatus/edit")
    public Long editOrderStatus(@RequestBody OrderStatus orderStatus, HttpServletRequest request) throws Exception {
        String uuid = request.getHeader("X-UUID");
        Long userId = CommonUtils.getUserId(CommonUtils.getToken(request), uuid);
        service.update(orderStatus, userId, OrderStatus.class);
        return orderStatus.getId();
    }

    @DeleteMapping(path = "/api/orderStatus/remove/{id}")
    public Long removeOrderStatus(@PathVariable Long id) {
        service.delete(id, OrderStatus.class);
        return id;
    }

    @GetMapping(path = "/api/orderStatus/{id}")
    public OrderStatus getOrderStatus(@PathVariable Long id) {
        return service.findOne(OrderStatus.class, id);
    }

    @GetMapping(path = "/api/orderStatus")
    public List<OrderStatus> listOrderStatus() {
        return service.findAll(OrderStatus.class);
    }

}

