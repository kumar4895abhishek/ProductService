package com.example.productservice.services;


import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.dtos.SearchRequestDto;
import com.example.productservice.models.Product;
import com.example.productservice.models.SortParam;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    SearchService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

//    public List<GenericProductDto> searchProducts(String query,int pageNumber,int itemsPerPage )
//    {
//        PageRequest pageRequest=PageRequest.of(pageNumber,itemsPerPage);
//
//        List<Product> products=productRepository.findAllByTitleContaining(query,pageRequest);
//        List<GenericProductDto> genericProductDtos=new ArrayList<>();
//        for(Product product: products)
//        {
//            genericProductDtos.add(product.from(product));
//        }
//        return genericProductDtos;
//    }
public List<GenericProductDto> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParams) {
    //
//        Sort sort = Sort.by("title").ascending()
//                .and(Sort.by("rating").descending())
//                .and(Sort.by("price").descending());

    Sort sort = null;
    if (sortParams.get(0).getSortType().equals("ASC"))
    {
        sort = Sort.by(sortParams.get(0).getSortParamName()).ascending();
    }
    else
    {
        sort = Sort.by(sortParams.get(0).getSortParamName()).descending();
    }
    for (int i = 1; i < sortParams.size(); i++)
    {
        if (sortParams.get(i).getSortType().equals("ASC"))
        {
            sort.and(Sort.by(sortParams.get(i).getSortParamName())).ascending();
        }
        else
        {
            sort.and(Sort.by(sortParams.get(i).getSortParamName())).descending();
        }
    }

    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
    List<Product> products = productRepository.findAllByTitleContaining(query, pageRequest,sort);
    List<GenericProductDto> genericProductDtos = new ArrayList<>();
    for (Product product : products) {
        genericProductDtos.add(product.from(product));
    }

    //
    return genericProductDtos;
}
}
