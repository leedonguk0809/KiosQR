package org.zerock.wcup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.wcup.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
