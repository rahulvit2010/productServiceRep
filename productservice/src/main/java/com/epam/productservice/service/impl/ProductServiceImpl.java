package com.epam.productservice.service.impl;

import com.epam.productservice.dao.ProductCategoryRepository;
import com.epam.productservice.dao.ProductRepository;
import com.epam.productservice.entity.Product;
import com.epam.productservice.entity.ProductCategory;
import com.epam.productservice.exception.NoContentFoundException;
import com.epam.productservice.exception.ProductCategoryNotFound;
import com.epam.productservice.exception.ProductNotFoundException;
import com.epam.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public List<Product> retreiveAll()
    {
        return Optional.ofNullable(productRepository.findAll()).orElseThrow(
                ()-> new NoContentFoundException("Sorry, there are no records found"));
    }

    @Override
    public Product retreiveById(int id) {
        return productRepository.findById(id).orElseThrow(
                ()-> new ProductNotFoundException("Sorry Product  with id " +id+" not found ")
        );
    }

    @Override
    public Product save(Product product) {
            checkExistence(product.getProductCategory().getId());
            productRepository.save(product);
            return productRepository.findById(product.getProductId()).orElseThrow(
                    ()-> new RuntimeException("product is not saved in database")
            );


    }

    @Override
    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public boolean checkExistence(int id) {
        return productCategoryRepository.findById(id)
                .map( productCategory  -> true)
                .orElseThrow(
                        ()-> new ProductCategoryNotFound("product category is not valid"));
    }
}
