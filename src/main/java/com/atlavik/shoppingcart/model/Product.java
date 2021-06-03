package com.atlavik.shoppingcart.model;


import javax.persistence.*;

import java.util.UUID;


@Entity
@Table(name="products")
public class Product  extends  TimeStampDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;

    @Column(name="description", nullable = false)
    private  String description;

    @Column(name="category", nullable = false)
    private String category;

    @Column(name="price", nullable = false)
    private double price;

   // @ManyToOne
   // @JoinColumn(name="shoppingcart_id",nullable = false)
   // private ShoppingCart shoppingCart = new ShoppingCart();


    public Product(UUID id, String description, String category, double price) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Product() {
       super();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }



}
