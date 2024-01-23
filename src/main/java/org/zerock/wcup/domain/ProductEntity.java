package org.zerock.wcup.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "store")
@Table(name = "tbl_product")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private String gubun;



    @ElementCollection (fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ProductEntityImage> productImages = new HashSet<>();


    public void addImage(String fileName) {
        productImages.add(new ProductEntityImage(productImages.size(), fileName));

    }

    public void clearImages() {
        this.productImages.clear();
    }

}
