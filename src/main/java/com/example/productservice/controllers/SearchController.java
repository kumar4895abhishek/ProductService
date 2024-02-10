package com.example.productservice.controllers;


import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.dtos.SearchRequestDto;
import com.example.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController
{

    private SearchService searchService;

    SearchController(SearchService searchService)
    {
        this.searchService=searchService;
    }

//    @PostMapping
//    public List<GenericProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto)
//    {
//        return searchService.searchProducts(searchRequestDto.getQuery(),searchRequestDto.getPageNumber(),searchRequestDto.getItemsPerPage());
//    }
@PostMapping
public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto requestDto) {
    List<GenericProductDto> genericProductDtos = searchService.searchProducts(requestDto.getQuery(), requestDto.getPageNumber(),
            requestDto.getItemsPerPage(), requestDto.getSortParams());
    Page<GenericProductDto> genericProductDtoPage = new PageImpl<>(
            genericProductDtos
    );
    return genericProductDtoPage;
}
}
