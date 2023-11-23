package com.example.productservice.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends  BaseModel
{

    private Long id;

    private String title;

    private int price;

    private String category;

    private String description;

    private String image;


}
