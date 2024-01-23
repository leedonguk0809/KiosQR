package org.zerock.wcup.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString(exclude = {"orderEntity","product"})
@Getter
@Table(name = "tbl_order_detail")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odnum; //주문 상세 번호

    private int qty;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;
}
