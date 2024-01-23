package org.zerock.wcup.service;


import jakarta.transaction.Transactional;
import org.zerock.wcup.dto.OrderRequestDTO;

@Transactional
public interface OrderService {

    Long register(OrderRequestDTO orderRequestDTO);
}
