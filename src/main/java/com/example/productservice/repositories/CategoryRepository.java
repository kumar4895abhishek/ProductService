package com.example.productservice.repositories;

import com.example.productservice.models.BaseModel;
import com.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category,UUID>
{

}
