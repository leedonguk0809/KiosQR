package org.zerock.wcup.domain;


import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@ToString
@NoArgsConstructor
public class ProductEntityImage {

    private int ord;
    private String fileName;

    public ProductEntityImage(int ord, String fileName) {
        this.ord = ord;
        this.fileName = fileName;
    }
}
