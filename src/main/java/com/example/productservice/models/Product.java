package com.example.productservice.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends  BaseModel
{



    private String title;

    @OneToOne(optional = false,cascade = {CascadeType.REMOVE, CascadeType.ALL})
    @JoinColumn(nullable = false)
    private Price price;

    private String description;

    private String image;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Category category;

    private int inventoryCount;


}
