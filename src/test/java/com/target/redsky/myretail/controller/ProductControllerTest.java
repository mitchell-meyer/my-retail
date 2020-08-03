package com.target.redsky.myretail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.redsky.myretail.entity.Price;
import com.target.redsky.myretail.entity.Product;
import com.target.redsky.myretail.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProductService productService;

  @Test
  public void getProductTest() throws Exception {
    long productId = 1;

    Product product = new Product();
    product.setId(productId);
    product.setName("Test Product");

    when(productService.getProduct(productId)).thenReturn(product);

    this.mockMvc.perform(get("/products/" + productId))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("{\"id\":1,\"name\":\"Test Product\",\"currentPrice\":null,\"currencyCode\":null}")));
  }

  @Test
  public void savePriceTest() throws Exception {
    long productId = 1;

    Price price = new Price();
    price.setCurrentPrice(BigDecimal.valueOf(89.99));
    price.setCurrencyCode("USD");

    this.mockMvc.perform(
            put("/products/" + productId)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(price))
            ).andDo(print())
            .andExpect(status().isOk());

    Price savedPrice = new Price();
    savedPrice.setId(productId);
    savedPrice.setCurrentPrice(BigDecimal.valueOf(89.99));
    savedPrice.setCurrencyCode("USD");

    verify(productService).saveProductPrice(savedPrice);
  }
}