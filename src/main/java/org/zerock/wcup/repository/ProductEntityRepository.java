package org.zerock.wcup.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.wcup.domain.ProductEntity;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {



    @EntityGraph(attributePaths = {"images"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select p from ProductEntity p  where p.pno = :pno")
    ProductEntity getOne(@Param("pno") Long pno);

}
