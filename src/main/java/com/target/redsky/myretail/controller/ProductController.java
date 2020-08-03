package com.target.redsky.myretail.controller;

import com.target.redsky.myretail.entity.Price;
import com.target.redsky.myretail.entity.Product;
import com.target.redsky.myretail.service.ProductService;
import org.springframework.web.bind.annotation.*;

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
   * Save product pricing information.
   *
   * @param productId ID of the product for which to save pricing information.
   */
  @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
  public void savePrice(@PathVariable(value="productId") long productId,
                        @RequestBody Price price) {
    price.setId(productId);
    productService.saveProductPrice(price);
  }
}
