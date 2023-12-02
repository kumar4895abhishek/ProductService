package com.example.productservice.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category<C extends BaseModel, U> extends BaseModel
{

    @Column(unique = true,nullable = false)
    private String name;


    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
