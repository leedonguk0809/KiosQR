package org.zerock.wcup.service;


import jakarta.transaction.Transactional;
import org.zerock.wcup.dto.ProductRegisterDTO;

@Transactional
public interface ProductService {

    Long register(ProductRegisterDTO dto);
}
