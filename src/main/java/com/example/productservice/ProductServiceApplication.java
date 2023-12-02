package com.example.productservice;

import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.OrderRepository;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;
    private final OrderRepository orderRepository;

    public ProductServiceApplication(CategoryRepository categoryRepository,
                                     ProductRepository productRepository,
                                     PriceRepository priceRepository,
                                     OrderRepository orderRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Price price = new Price();
        price.setCurrency("INR");
        price.setValue(100000);

        Price savePrice = priceRepository.save(price);

        Category category=new Category();
        category.setName("Apple Devices");
        Category savedCategoy = categoryRepository.save(category);


        Product product = new Product();
        product.setTitle("iPhone 15 pro");
        product.setDescription("Best iPhone ever");
        product.setCategory(savedCategoy);
        product.setPrice(price);

        Product savedProduct = productRepository.save(product);


    }


}
