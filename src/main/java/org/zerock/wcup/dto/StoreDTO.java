package org.zerock.wcup.dto;


import lombok.Data;

@Data
public class StoreDTO {

    private Long sno;

    private String pw;

    private String title;

    private String qrImage;

    public StoreDTO(Long sno, String pw, String title, String qrImage) {
        this.sno = sno;
        this.pw = pw;
        this.title = title;
        this.qrImage = qrImage;
    }
}
