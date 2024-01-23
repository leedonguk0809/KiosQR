package org.zerock.wcup.service;


import jakarta.transaction.Transactional;
import org.zerock.wcup.dto.PageRequestDTO;
import org.zerock.wcup.dto.PageResponseDTO;
import org.zerock.wcup.dto.StoreDTO;
import org.zerock.wcup.dto.UpdateTokenDTO;

@Transactional
public interface StoreService {


    Long register(StoreDTO storeDTO);

    void updateToken(UpdateTokenDTO updateTokenDTO);

     PageResponseDTO<StoreDTO> list(PageRequestDTO pageRequestDTO);

}
