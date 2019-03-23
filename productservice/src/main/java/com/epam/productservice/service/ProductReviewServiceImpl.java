package com.epam.productservice.service;

import com.epam.productservice.entity.Review;
import com.epam.productservice.exception.handler.ProductResponseErrorHandler;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    private RestTemplate restTemplate;

    private EurekaClient eurekaClient;

    final String SERVICE_NAME = "review-service";

    public ProductReviewServiceImpl(EurekaClient eurekaClient) {
        this.restTemplate = new RestTemplate();
        this.eurekaClient = eurekaClient;
    }

    @Override
    public List<Review> getReviews(int productId) {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);

        return restTemplate.exchange(getServiceUrl() + "/api/" +productId+"/reviews"
                , HttpMethod.GET
                ,  httpEntity
                , new ParameterizedTypeReference<List<Review>>() {
                }).getBody();

    }

    @Override
    public Review AddProductReview(Review review, int productId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(review, httpHeaders);
        return restTemplate.exchange("http://" +SERVICE_NAME +"/api/"+productId+"/reviews/"
                , HttpMethod.POST
                ,  httpEntity
                , new ParameterizedTypeReference<Review>()
                {}).getBody();
    }

    @Override
    public Review updateProductReview(Review review, int productId, int reviewId) {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(review, httpHeaders);
       return restTemplate.exchange("http://" +SERVICE_NAME +"/api/"+productId+"/reviews/"+ reviewId
                , HttpMethod.PUT
                ,  httpEntity
                , new ParameterizedTypeReference<Review>()
                {}).getBody();

    }

    @Override
    public ResponseEntity<Object> deleteProductReview(int productId, int reviewId) {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);
        restTemplate.setErrorHandler(new ProductResponseErrorHandler());
         return  restTemplate.exchange("http://" +SERVICE_NAME +"/api/"+productId+"/reviews/"+ reviewId
                , HttpMethod.DELETE
                ,  httpEntity
                , Object.class
                );

    }

    private String getServiceUrl() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(SERVICE_NAME, false);
        return instance.getHomePageUrl();
    }
}
