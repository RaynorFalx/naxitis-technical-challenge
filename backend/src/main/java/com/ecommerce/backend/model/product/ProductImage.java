package com.ecommerce.backend.model.product;

import com.ecommerce.backend.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT_IMAGE")
public class ProductImage extends BaseEntity {

    @Lob
    @Column(name = "IMAGE_DATA", columnDefinition = "BYTEA")
    private String filePath;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "is_primary")
    private boolean primary;

    //TODO S3 INTEGRATION
//    @ManyToOne
//    @JoinColumn(name = "PRODUCT_ID")
//    private Product product;
}
