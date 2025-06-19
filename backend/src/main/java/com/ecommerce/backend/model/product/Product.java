package com.ecommerce.backend.model.product;

import com.ecommerce.backend.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    //TODO S3 INTEGRATION
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductImage> productImages;
}
