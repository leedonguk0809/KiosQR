package org.zerock.wcup.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.wcup.dto.OrderRequestDTO;
import org.zerock.wcup.service.OrderService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/orders")
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("")
    public java.util.Map<String, Long> register( @RequestBody OrderRequestDTO orderRequestDTO) {

        log.info("----------------------------------------------");
        log.info("order register---------------------------------");
        log.info("----------------------------------------------");
        log.info("----------------------------------------------");

        log.info(orderRequestDTO);

        Long onum = orderService.register(orderRequestDTO);


        return Map.of("orderNum", onum);
    }


}
