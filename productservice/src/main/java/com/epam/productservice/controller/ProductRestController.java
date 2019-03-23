package com.epam.productservice.controller;

import com.epam.productservice.entity.Product;
import com.epam.productservice.entity.Review;
import com.epam.productservice.exception.handler.ProductResponseErrorHandler;
import com.epam.productservice.service.ProductReviewService;
import com.epam.productservice.service.ProductService;
import com.netflix.appinfo.InstanceInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductReviewService productReviewService;



    @GetMapping("/products")
    @ApiOperation(value = "display all the available products", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity<Object> findAll()
    {
        List<Product> products=productService.retreiveAll();
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "it find product by Id and provide all its details", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity<Object> findProduct(@PathVariable int id)
    {
        Product product= productService.retreiveById(id);
        product.setReviews(productReviewService.getReviews(id));
        List<Object> result= new ArrayList<>();
        result.add(product);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping("/products/{productId}/reviews")
    @ApiOperation(value = "it adds review for product", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
    })
    public ResponseEntity<Object> AddProductReview(@RequestBody Review review,@PathVariable int productId)
    {
       Review addedReview= productReviewService.AddProductReview(review,productId);
        return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
    }


    @PutMapping("/products/{productId}/reviews/{reviewId}")
    @ApiOperation(value = "it updates existing product reviews ", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
    })
    public ResponseEntity<Object> updateProductReview(@RequestBody Review review ,
                                                      @PathVariable int productId ,
                                                      @PathVariable  int reviewId)
    {
       Review updatedReview = productReviewService.updateProductReview(review,productId,reviewId);
        return  new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }




    @DeleteMapping("/products/{productId}/reviews/{reviewId}")
    @ApiOperation(value = "it Delete existing product reviews ", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO_CONTENT")
    })
    public ResponseEntity<Object> deleteProductReview(@PathVariable int productId
                                                    ,@PathVariable int reviewId)
    {
        ResponseEntity<Object> reviews = productReviewService.deleteProductReview(productId,reviewId);
        return new ResponseEntity<>(reviews.getStatusCode());
    }


    @PostMapping("/products")
    @ApiOperation(value = "it Creates new Product entry ", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
    })
    public ResponseEntity<Object> AddProduct(@RequestBody Product product)
    {
        Product savedProduct=productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }



    @PutMapping("/products")
    @ApiOperation(value = "it updates existing product ", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
    })
    public ResponseEntity<Object> updateProduct(@RequestBody Product product)
    {
        Product savedProduct= productService.save(product);
        return  new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }


    @DeleteMapping("/products/{productId}")
    @ApiOperation(value = "it Delete existing product ", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO_CONTENT")
    })
    public ResponseEntity<Object> deleteProduct(@PathVariable int productId)
    {
        productService.retreiveById(productId);
        productService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
