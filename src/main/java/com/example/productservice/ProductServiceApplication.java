package com.example.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {
//    private final CategoryRepository categoryRepository;
//    private final ProductRepository productRepository;
//    private final PriceRepository priceRepository;
//    private final OrderRepository orderRepository;
//
//    public ProductServiceApplication(CategoryRepository categoryRepository,
//                                     ProductRepository productRepository,
//                                     PriceRepository priceRepository,
//                                     OrderRepository orderRepository) {
//        this.categoryRepository = categoryRepository;
//        this.productRepository = productRepository;
//        this.priceRepository = priceRepository;
//        this.orderRepository = orderRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {

//        Price price = new Price();
//        price.setCurrency("INR");
//        price.setValue(4999);
//        Price savedPrice = priceRepository.save(price);
//
//        Category category = new Category();
//        category.setName("JJJ");
//        Category savedCategoy = categoryRepository.save(category);
//
//        Product product = new Product();
//        product.setTitle(" JJJ");
//        product.setDescription(" JJJ");
//        product.setCategory(savedCategoy);
//        product.setPrice(price);
//        Product savedProduct = productRepository.save(product);

//        List<Product> productList=productRepository.findAllByTitle("Samsung Foldable Phone");
//
//        List<Product> productList1=productRepository.findProductByCategory_Name("Apple Devices");
//
//        List<Product> productList2=productRepository.findProductByPrice_ValueBetween(20000,40000);


  //  }


}
