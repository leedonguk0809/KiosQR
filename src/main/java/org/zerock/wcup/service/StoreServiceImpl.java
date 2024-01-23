package org.zerock.wcup.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.wcup.domain.Store;
import org.zerock.wcup.dto.*;
import org.zerock.wcup.repository.StoreRepository;

@Service
@Log4j2
@RequiredArgsConstructor

public class StoreServiceImpl implements  StoreService {

    private final StoreRepository storeRepository;

    private final ModelMapper modelMapper;


    public PageResponseDTO<StoreDTO> list(PageRequestDTO pageRequestDTO){

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("sno").descending()
        );
        Page<StoreDTO> result = storeRepository.listWithStoreCountDTO(pageable);

        long total = result.getTotalElements();
        int current = pageRequestDTO.getPage();
        int size = pageRequestDTO.getSize();

        return new PageResponseDTO<>(total,current,size,result.toList());
    }



    @Override
    public Long register(StoreDTO storeDTO) {

        Store store = modelMapper.map(storeDTO, Store.class);



        storeRepository.save(store);

        return store.getSno();
    }

    @Override
    public void updateToken(UpdateTokenDTO updateTokenDTO) {

            Store store = storeRepository.getOne(updateTokenDTO.getSno());

            store.changeToken(updateTokenDTO.getToken());

            storeRepository.save(store);
    }
}
