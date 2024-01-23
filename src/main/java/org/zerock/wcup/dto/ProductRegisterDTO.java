package org.zerock.wcup.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.wcup.domain.Store;

import java.util.List;


@Data
public class ProductRegisterDTO {

    private Long pno;
    private String pname;
    private int price;

    private Long sno;

    private String gubun;

    private List<String> uploadFileNames;

    private List<MultipartFile> files;
}
