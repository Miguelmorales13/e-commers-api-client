package com.miguel.morales.ecommers.api.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguel.morales.ecommers.api.categories.CategoriesProductModel;
import com.miguel.morales.ecommers.api.imagesProducts.ImagesProductModel;
import com.miguel.morales.ecommers.converts.ImageConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")

@SQLDelete(sql = "UPDATE products SET deleted_at = NOW() WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted_at is null")
public class ProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "main_image", length = 200, nullable = false)
    @Convert(converter = ImageConverter.class)
    private String mainImage;

    @Column(name = "has_multiples_images", nullable = false)
    private Boolean hasMultiplesImages = false;

    @Column(name = "price", precision = 8, nullable = false)
    private Double price;

    @Column(name = "price_discount", precision = 8, nullable = false)
    private Double priceDiscount;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "brand", length = 100, nullable = false)
    private String brand;

    @Column(name = "is_in_discount", nullable = false)
    private Boolean isInDiscount = false;

    @Column(name = "active", nullable = false)
    private Boolean active = true;


    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ImagesProductModel> images;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, columnDefinition = "bigint")
    private CategoriesProductModel category;


    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "deleted_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}
