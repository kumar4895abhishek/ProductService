package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto
{
    private Long id;

    private String title;

    private String desc;

    private int price;

    private String image;

    private String category;
}
