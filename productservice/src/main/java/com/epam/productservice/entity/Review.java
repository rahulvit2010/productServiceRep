package com.epam.productservice.entity;

import java.util.Objects;

public class Review {

    private int id;


    private String description;


    private int rating;


    private int productId;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRaiting(int raiting) {
        this.rating = raiting;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Review()
    {

    }
    public Review(String description, int raiting) {
        this.description = description;
        this.rating = raiting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id &&
                rating == review.rating &&
                productId == review.productId &&
                Objects.equals(description, review.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, rating, productId);
    }
}


