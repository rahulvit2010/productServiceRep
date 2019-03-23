package com.epam.productservice.exception.handler;


import com.epam.productservice.errorresponse.ProductErrorResponse;
import com.epam.productservice.exception.NoContentFoundException;
import com.epam.productservice.exception.ProductCategoryNotFound;
import com.epam.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc)
    {
        ProductErrorResponse error= new ProductErrorResponse();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception exc)
    {
        ProductErrorResponse error= new ProductErrorResponse();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(NoContentFoundException exc)
    {
        ProductErrorResponse error= new ProductErrorResponse();
        error.setStatusCode(HttpStatus.OK.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductCategoryNotFound exc)
    {
        ProductErrorResponse error= new ProductErrorResponse();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
