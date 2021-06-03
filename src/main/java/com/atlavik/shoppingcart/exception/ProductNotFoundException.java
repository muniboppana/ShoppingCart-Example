package com.atlavik.shoppingcart.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException  extends  RuntimeException{

    private  String message;

    public ProductNotFoundException(String message){
        this.message = message ;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
