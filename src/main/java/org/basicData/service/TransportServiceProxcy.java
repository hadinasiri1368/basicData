package org.basicData.service;

import org.basicData.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;




@FeignClient(name = "TRANSPORT")
public interface TransportServiceProxcy {
    @GetMapping(path = "/transport/person")
    Page<PersonDto> getPerson(@RequestHeader("Authorization") String token, @RequestHeader("X-UUID") String uuid);

}

