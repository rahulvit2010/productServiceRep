package com.epam.productservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="product_details")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetail that = (ProductDetail) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(description, that.description) &&
                Objects.equals(sold_by, that.sold_by) &&
                Objects.equals(discount_percentage, that.discount_percentage) &&
                Objects.equals(discounted_money, that.discounted_money) &&
                Objects.equals(brand_name, that.brand_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, sold_by, discount_percentage, discounted_money, brand_name);
    }

    @Column(name="description")
    private String description;

    @Column(name="price")
    private int price;

    @Column(name="sold_by")
    private String sold_by;

    @Column(name="discount_percentage")
    private String discount_percentage;

    @Column(name="discounted_money")
    private String discounted_money;

    @Column(name="brand_name")
    private String brand_name;

    public ProductDetail()
    {

    }

    public ProductDetail(String description, int price, String sold_by, String discount_percentage, String discounted_money, String brand_name) {
        this.description = description;
        this.price = price;
        this.sold_by = sold_by;
        this.discount_percentage = discount_percentage;
        this.discounted_money = discounted_money;
        this.brand_name = brand_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSold_by() {
        return sold_by;
    }

    public void setSold_by(String sold_by) {
        this.sold_by = sold_by;
    }

    public String getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(String discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public String getDiscounted_money() {
        return discounted_money;
    }

    public void setDiscounted_money(String discounted_money) {
        this.discounted_money = discounted_money;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

}
