package com.example.productservice.controllersTest;


import com.example.productservice.controllers.ProductController;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.services.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Captor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentCaptor;

@SpringBootTest
public class ProductControllerTest
{
    @MockBean
    private ProductService productService;

    @Inject
    private ProductController productController;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;


    @Test
    void testProductByIdMocking() throws ProductNotFoundException {
        GenericProductDto genericProductDto=new GenericProductDto();
        when(productService.getProductById(any(Long.class))).thenReturn(genericProductDto);

        GenericProductDto genericProductDto1=productController.getProductById(100L);
        assertEquals(genericProductDto,genericProductDto1);
    }


    @Test
    void testProductByIdMockingException() throws ProductNotFoundException
    {
        when(productService.getProductById(10L)).thenThrow(ProductNotFoundException.class);
//        GenericProductDto genericProductDto=productController.getProductById(1000L);

        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(10L));

    }

    @Test
    void testProductByIdCustomLogic() throws ProductNotFoundException {
        GenericProductDto genericProductDto=new GenericProductDto();
        when(productService.getProductById(10L)).thenReturn(genericProductDto);
        GenericProductDto genericProductDto1=productController.getProductById(10L);

        assertEquals(genericProductDto,genericProductDto1);

    }

    @Test
    @DisplayName("testProductControllerCallsProductServiceWithSameProductIdAsInput")
    void testIfSameInput() throws ProductNotFoundException {

        Long id = 100L;

        when(productService.getProductById(id)).thenReturn(new GenericProductDto());

        GenericProductDto genericProductDto=productController.getProductById(id);

        verify(productService).getProductById(argumentCaptor.capture());

        assertEquals(id, argumentCaptor.getValue());


    }





}
