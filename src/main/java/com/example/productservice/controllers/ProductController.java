package com.example.productservice.controllers;


import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{

    public ProductService productService;

    ProductController(@Qualifier ("FakeStoreProductService") ProductService productService)
    {
        this.productService=productService;
    }


    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException
    {
        System.out.println("ID is "+id);
        return productService.getProductById(id);
    }
    @GetMapping()
    public List<GenericProductDto> getAllProducts()
    {

        return productService.getAllProducts();


    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id)
    {
        return productService.deleteProductById(id);
    }


    @PostMapping()
    private GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto)
    {
    return productService.createProduct(genericProductDto);
    }


    @PutMapping()
    private GenericProductDto updateProductById(@PathVariable("id") Long id,@RequestBody GenericProductDto genericProductDto)
    {
        return productService.updateProductById(id,genericProductDto);
    }

}
