package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.thirdpartClients.fakeStoreClient.FakeStoreClientAdapater;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("FakeStoreProductService")
public class FakeStoreProductService implements  ProductService
{
    //private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private FakeStoreClientAdapater fakeStoreClientAdapater;
    private RedisTemplate<String, FakeStoreProductDto> redisTemplate;


    FakeStoreProductService(FakeStoreClientAdapater fakeStoreClientAdapater,RedisTemplate redisTemplate)
        {
            this.fakeStoreClientAdapater=fakeStoreClientAdapater;
            this.redisTemplate = redisTemplate;
        }


    GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto)
    {
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDesc());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setImage(fakeStoreProductDto.getImage());

        return genericProductDto;

    }

    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {


        System.out.println("CALLING PRODUCT BY ID");
//        logger.debug("Debug message");
//        logger.warn("Warning message");
//        logger.info("Info message");
//
//        logger.error("Error message");
        FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get("PRODUCTS", id);

        if (fakeStoreProductDto != null) {
            return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
        }
        fakeStoreProductDto = fakeStoreClientAdapater.getProductById(id);
        redisTemplate.opsForHash().put("PRODUCTS", id, fakeStoreProductDto);



        return convertFakeStoreDtoToGenericProductDto(fakeStoreClientAdapater.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts()
    {

        List<FakeStoreProductDto> fakeStoreProductDtos=fakeStoreClientAdapater.getAllProducts();
        List<GenericProductDto> genericProductDtos=new ArrayList<>();


        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos)
        {
            genericProductDtos.add(convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto));
        }
        System.out.println("CALLING ALL PRODUCTS");
        return genericProductDtos;

    }

    @Override
    public GenericProductDto deleteProductById(Long id)
    {
        System.out.println("CALLING DELETE");

        return convertFakeStoreDtoToGenericProductDto(fakeStoreClientAdapater.deleteProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto)
    {
        System.out.println("CALLING CREATE PRODUCT");

        return convertFakeStoreDtoToGenericProductDto(fakeStoreClientAdapater.createProduct(genericProductDto));
    }

    @Override
    public GenericProductDto updateProductById(Long id,GenericProductDto genericProductDto)
    {

        System.out.println("CALLING UPDATE");

        return convertFakeStoreDtoToGenericProductDto(fakeStoreClientAdapater.updateProductById(id,genericProductDto));
    }


}
