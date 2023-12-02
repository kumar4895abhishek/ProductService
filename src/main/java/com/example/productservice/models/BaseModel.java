package com.example.productservice.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public class BaseModel
{
    @Id
    @GeneratedValue(generator = "generator_name")
    @GenericGenerator(name = "generator_name",strategy = "uuid2")
    @Column(name = "id",columnDefinition = "binary(16)",nullable = false,updatable = false)
    private UUID id;
}
