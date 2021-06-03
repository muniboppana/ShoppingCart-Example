package com.atlavik.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name= "shoppingcarts")
public class ShoppingCart extends  TimeStampDetails   {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cartId")
    private Long cartId;

    @Column(name="countryCode" , nullable = false)
    private String countryCode;

    @Column(name="currency" , nullable = false)
    private String currency;

    @JsonProperty("products")
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="shoppingcarts.cartId")
    private List<Product> products  = new ArrayList<Product>();

    public ShoppingCart() {
        super();
    }

    public ShoppingCart( String countryCode, String currency, List<Product> products) {
        this.countryCode = countryCode;
        this.currency = currency;
        this.products = products;

    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Product> getProducts() {

        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public void addProductToShoppingCart(List<Product> productList) {
     if(productList.isEmpty()){
         this.products.add(new Product());
     } else {
         for (Product product : productList) {
             this.products.add(product);
         }
     }
    }







}
