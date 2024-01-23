package org.zerock.wcup.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.wcup.domain.OrderDetail;
import org.zerock.wcup.domain.OrderEntity;
import org.zerock.wcup.domain.ProductEntity;
import org.zerock.wcup.dto.OrderRequestDTO;
import org.zerock.wcup.dto.OrderRequestDetailDTO;
import org.zerock.wcup.repository.OrderDetailRepository;
import org.zerock.wcup.repository.OrderEntityRepository;
import org.zerock.wcup.repository.ProductEntityRepository;


@Service
@Log4j2
@RequiredArgsConstructor

public class OrderServiceImpl  implements OrderService{

    private final ModelMapper modelMapper;

    private final OrderEntityRepository orderEntityRepository;

    private final OrderDetailRepository orderDetailRepository;



    @Override
    public Long register(OrderRequestDTO orderRequestDTO) {

        OrderEntity orderEntity = OrderEntity.builder()
                .buyer(orderRequestDTO.getDeviceId())
                .build();

        orderEntityRepository.save(orderEntity);

        Long onum = orderEntity.getOnum();

        for (OrderRequestDetailDTO item : orderRequestDTO.getItems()) {

            ProductEntity productEntity = ProductEntity.builder().pno(item.getPno()).build();

            OrderDetail orderDetail = OrderDetail.builder()
                    .product(productEntity)
                    .qty(item.getQty())
                    .orderEntity(orderEntity)
                    .build();

            orderDetailRepository.save(orderDetail);
        }


        return onum;
    }
}
