package com.miguel.morales.ecommers.api.imagesProducts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguel.morales.ecommers.api.products.ProductModel;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images_products")
@SQLDelete(sql = "UPDATE images_products SET deleted_at = NOW() WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted_at is null")
public class ImagesProductModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private int id;

    @Column(name = "file", length = 100, nullable = false)
    @Convert(converter = ImageConverter.class)
    private String file;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "size", length = 30, nullable = false)
    private String size;

    @Column(name = "dimensions", length = 30, nullable = false)
    private String dimensions;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, columnDefinition = "bigint")
    private ProductModel product;


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
