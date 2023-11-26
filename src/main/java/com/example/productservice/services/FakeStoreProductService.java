package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements  ProductService
{

    private RestTemplateBuilder restTemplateBuilder;

    private String specificProductUrl = "https://fakestoreapi.com/products/{id}";

    private String genericProductUrl = "https://fakestoreapi.com/products";

    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
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
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(specificProductUrl,FakeStoreProductDto.class,id);

        System.out.println("CALLING PRODUCT BY ID");

        GenericProductDto genericProductDto=convertFakeStoreDtoToGenericProductDto(responseEntity.getBody());

        if(genericProductDto ==null)
        {
            throw new ProductNotFoundException("Product with id:  "+id+" doesn't exist.");
        }

        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts()
    {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity=restTemplate.getForEntity(genericProductUrl,FakeStoreProductDto[].class);

        List<GenericProductDto> genericProductDtos=new ArrayList<>();
        List<FakeStoreProductDto> fakeStoreProductDtos=List.of(responseEntity.getBody());

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
        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity <FakeStoreProductDto> responseEntity=
                restTemplate.execute(specificProductUrl,HttpMethod.DELETE,requestCallback,responseExtractor, id);
        System.out.println("CALLING DELETE");

        return convertFakeStoreDtoToGenericProductDto(responseEntity.getBody());

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto)
    {

        RestTemplate restTemplate= restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.postForEntity(genericProductUrl,genericProductDto,FakeStoreProductDto.class);

        System.out.println("CALLING CREATE PRODUCT");

        return convertFakeStoreDtoToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public GenericProductDto updateProductById(Long id,GenericProductDto genericProductDto) {
        RestTemplate restTemplate= restTemplateBuilder.build();

        //RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
//        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
//                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(new HttpEntity<>(GenericProductDto.class));
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);


//        ResponseEntity <FakeStoreProductDto> responseEntity=
//                restTemplate.execute(specificProductUrl,HttpMethod.PUT,requestCallback,responseExtractor, id);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.exchange(specificProductUrl, HttpMethod.PUT, new HttpEntity<>(genericProductDto) , FakeStoreProductDto.class, id);
        System.out.println("CALLING UPDATE");

        return convertFakeStoreDtoToGenericProductDto(responseEntity.getBody());
    }
}
