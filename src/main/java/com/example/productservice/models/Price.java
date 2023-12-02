package com.example.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "price")
public class Price extends  BaseModel
{
    private String currency;

    private double value;
}
