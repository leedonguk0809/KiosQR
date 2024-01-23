package org.zerock.wcup.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.wcup.domain.ProductEntity;
import org.zerock.wcup.dto.ProductRegisterDTO;
import org.zerock.wcup.repository.ProductEntityRepository;

@Service
@Log4j2
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductEntityRepository productEntityRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ProductRegisterDTO dto) {

        log.info("register...............");

        ProductEntity product = modelMapper.map(dto, ProductEntity.class);

        dto.getUploadFileNames().forEach(img -> {
            product.addImage(img);
        });

        log.info(product);

        productEntityRepository.save(product);

        return product.getPno();
    }
}
