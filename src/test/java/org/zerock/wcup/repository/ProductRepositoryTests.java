package org.zerock.wcup.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.wcup.domain.ProductEntity;
import org.zerock.wcup.domain.Store;

import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

    @Autowired
    ProductEntityRepository productEntityRepository;

    @Test
    public void testInsert() {

        for (long i = 1; i < 90 ; i++) {

            Store store = Store.builder().sno( (long)(Math.ceil(i/10.0)) ).build();

            ProductEntity product = ProductEntity.builder()
                    .pname("상품......"+i)
                    .pno( 100 * (i %10))
                    .gubun("FOOD")
                    .store(store)
                    .build();

            product.addImage(UUID.randomUUID()+"_P"+i+"--test1.jpg");
            product.addImage(UUID.randomUUID()+"_P"+i+"--test2.jpg");

            productEntityRepository.save(product);

        }


    }

}
