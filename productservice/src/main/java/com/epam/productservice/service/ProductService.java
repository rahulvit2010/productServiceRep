package com.epam.productservice.service;

import com.epam.productservice.entity.Product;
import com.epam.productservice.entity.ProductCategory;

import java.util.List;

public interface ProductService {

    public List<Product> retreiveAll();

    public Product retreiveById(int id);

    public Product save(Product product);

    public void deleteById(int productcId);

    public boolean checkExistence(int id);

}
