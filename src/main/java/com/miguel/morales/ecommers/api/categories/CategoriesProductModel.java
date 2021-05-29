package com.miguel.morales.ecommers.api.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguel.morales.ecommers.api.products.ProductModel;
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
@Table(name = "categories_products")
@SQLDelete(sql = "UPDATE categories_products SET deleted_at = NOW() WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted_at is null")
public class CategoriesProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private int id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "nivel", length = 1)
    private int nivel;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductModel> products;

    @OneToMany
    @JoinColumn(referencedColumnName = "id", table = "categories_products", name = "category_id")
    private List<CategoriesProductModel> categories;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "id", table = "categories_products", name = "category_id", columnDefinition = "bigint")
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
