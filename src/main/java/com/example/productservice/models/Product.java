package com.example.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends  BaseModel
{



    private String title;

    private int price;

    @ManyToOne
    private Category category;

    private String description;

    private String image;


}
