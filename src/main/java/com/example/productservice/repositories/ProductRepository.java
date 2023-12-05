package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>

{


    List<Product> findAllByTitle(String title);

    List<Product> findProductByCategory_Name(String categoryName);

    List<Product> findProductByPrice_ValueBetween(Integer x,Integer y);
}
