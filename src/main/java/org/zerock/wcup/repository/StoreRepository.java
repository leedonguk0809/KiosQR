package org.zerock.wcup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.wcup.domain.Store;
import org.zerock.wcup.dto.StoreDTO;

public interface StoreRepository extends JpaRepository<Store, Long> {
    //Long sno, String pw, String title, String qrImage
    @Query("select " +
            " new org.zerock.wcup.dto.StoreDTO(s.sno, s.pw, s.title, s.qrImage) " +
            " from " +
            " Store s  ")
    Page<StoreDTO> listWithStoreCountDTO(Pageable pageable);
}
