package com.target.redsky.myretail.service;

import com.target.redsky.myretail.entity.Price;
import com.target.redsky.myretail.entity.Product;
import com.target.redsky.myretail.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

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

    Product product;
    if (optionalProduct.isPresent()) {
      product = optionalProduct.get();
    } else {
      product = new Product();
      product.setId(productId);
    }

    Optional<Price> optionalPrice = priceRepository.findById(productId);
    optionalPrice.ifPresent(price -> {
      product.setCurrentPrice(price.getCurrentPrice());
      product.setCurrencyCode(price.getCurrencyCode());
    });

    return product;
  }

  /**
   * Save product pricing information.
   *
   * @param price Pricing information for a product.
   */
  public void saveProductPrice(Price price) {
    priceRepository.save(price);
  }
}
