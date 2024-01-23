package org.zerock.wcup.dto;

import lombok.Data;

import java.util.List;


@Data
public class OrderRequestDTO {

    private String deviceId;

    private List<OrderRequestDetailDTO> items;



}
