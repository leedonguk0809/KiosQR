package org.zerock.wcup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.wcup.domain.OrderEntity;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {


    @Query("select o, od, p.pname, p.price, pi.fileName " +
            "from " +
            "  OrderEntity o inner join OrderDetail od on od.orderEntity = o " +
            "  inner join ProductEntity p on od.product = p " +
            "  inner join p.productImages pi " +
            "where pi.ord = 0 and  o.onum = :onum ")
    java.util.List<Object[]> getOrderDetails( @Param ("onum") Long onum);


    @Query("select " +
            " o.onum, p.pname, od.qty " +
            " from OrderEntity o inner join OrderDetail od on od.orderEntity = o " +
            " inner join ProductEntity p on od.product = p " +
            " inner join Store s on p.store = s " +
            " where s.sno = :sno and o.status = 0 order by od.odnum  ")
    java.util.List<Object[]> getOrderOfStore( @Param("sno") Long sno);

}
