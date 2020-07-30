package com.target.redsky.myretail.service;

import com.target.redsky.myretail.entity.Price;
import com.target.redsky.myretail.entity.Product;
import com.target.redsky.myretail.repository.PriceRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

  private static final Logger log = Logger.getLogger(ProductService.class.getName());

  private final ExternalProductService externalProductService;

  private final PriceRepository priceRepository;

  public ProductService(ExternalProductService externalProductService, PriceRepository priceRepository) {
    this.externalProductService = externalProductService;
    this.priceRepository = priceRepository;
  }

  /**
   * Retrieves product information from an external API and combines it with pricing information for the product.
   *
   * @param productId ID of the product information to retrieve.
   * @return Product information for the given product ID.
   */
  public Product getProduct(long productId) {
    Optional<Product> optionalProduct = externalProductService.getProductFromExternalApi(productId);
    if (optionalProduct.isPresent()) {
      Product product = optionalProduct.get();

      Optional<Price> optionalPrice = priceRepository.findById(productId);
      optionalPrice.ifPresent(price -> {
        product.setCurrentPrice(price.getCurrentPrice());
        product.setCurrencyCode(price.getCurrencyCode());
      });

      return product;
    } else {
      log.info(String.format("Request for product with ID [%d] was not found.", productId));
      return null;
    }
  }

  /**
   * TODO
   */
  public void saveProductPrice() {

  }
}
