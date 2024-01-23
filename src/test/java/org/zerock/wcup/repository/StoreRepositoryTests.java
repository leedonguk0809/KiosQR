package org.zerock.wcup.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.wcup.domain.Board;
import org.zerock.wcup.domain.Store;

import java.util.List;

@SpringBootTest
@Log4j2
public class StoreRepositoryTests {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testInertStore0() {

        Store store = Store.builder()
                .title("ADMIN")
                .pw(passwordEncoder.encode("1111"))
                .build();


        storeRepository.save(store);

    }

    @Test
    public void testList(){

        //페이지 번호 0부터 , 사이즈, 정렬
        Pageable pageable = PageRequest.of(0, 10, Sort.by("sno").descending());

        Page<Store> result = storeRepository.findAll(pageable);

        log.info(result);

        log.info(result.getTotalElements());
        log.info(result.getTotalPages());

        result.forEach(store -> log.info(store));
    }

}
