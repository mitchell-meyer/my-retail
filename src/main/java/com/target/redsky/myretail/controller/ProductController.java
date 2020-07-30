package com.target.redsky.myretail.controller;

import com.target.redsky.myretail.entity.Product;
import com.target.redsky.myretail.service.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Retrieve product information for a given product ID.
   *
   * @param productId ID of the product information to retrieve.
   * @return Product information for the given product ID.
   */
  @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
  public Product getProduct(@PathVariable(value="productId") long productId) {
    return productService.getProduct(productId);
  }

  /**
   * TODO
   *
   * @param productId
   */
  @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
  public void saveProduct(@PathVariable(value="productId") long productId) {
    productService.saveProductPrice();
  }
}
