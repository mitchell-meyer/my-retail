package com.target.redsky.myretail;

import com.target.redsky.myretail.controller.ProductController;
import com.target.redsky.myretail.repository.PriceRepository;
import com.target.redsky.myretail.service.ExternalProductService;
import com.target.redsky.myretail.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyRetailApplicationTests {

	@Autowired
	private ProductController productController;

	@Autowired
	private ProductService productService;

	@Autowired
	private ExternalProductService externalProductService;

	@Autowired
	private PriceRepository priceRepository;

	@Test
	void contextLoads() {
		assertThat(productController).isNotNull();
		assertThat(productService).isNotNull();
		assertThat(externalProductService).isNotNull();
		assertThat(priceRepository).isNotNull();
	}

}
