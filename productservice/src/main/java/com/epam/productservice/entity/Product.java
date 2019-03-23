package com.epam.productservice.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="product")
public class Product  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int productId;

    @Column(name="name")
    private String name;



    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_detail_id")
    private ProductDetail detail;

    @Transient
    List<Review> reviews;
    public Product()
    {

    }


    public Product(int productId, String name, ProductCategory productCategory, ProductDetail detail) {
        this.productId = productId;
        this.name = name;
        this.productCategory = productCategory;
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                Objects.equals(name, product.name) &&
                Objects.equals(productCategory, product.productCategory) &&
                Objects.equals(detail, product.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productId, name, productCategory, detail);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDetail getDetail() {
        return detail;
    }

    public void setDetail(ProductDetail detail) {
        this.detail = detail;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
