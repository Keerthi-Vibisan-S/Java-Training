package com.example.practice.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Product {
    @Id
    @SequenceGenerator(
            name="product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private int id;
    @NotBlank(message = "Name field cannot be empty")
    @Column(unique = true)
    private String name;
    @NotBlank(message = "Type field cannot be empty")
    private String type;
    @NotBlank(message = "Price field cannot be empty")
    private String price;
    private String description;

    Product(){}

    Product(int id, String name, String type, String price, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String desc) {
        this.description = desc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
