package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreProductDto implements Serializable
{
    private Long id;

    private String title;

    private String desc;

    private int price;

    private String image;

    private String category;
}
