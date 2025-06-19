package com.ecommerce.backend.model.product;

import com.ecommerce.backend.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATERORY")
public class Category extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;
}
