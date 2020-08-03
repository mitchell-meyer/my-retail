package com.target.redsky.myretail.service;

import com.target.redsky.myretail.entity.*;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ExternalProductService {

  private static final Logger log = Logger.getLogger(ExternalProductService.class.getName());

  private final RestTemplate restTemplate;

  public ExternalProductService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Retrieve product information from an external API.
   *
   * @param productId ID of the product information to retrieve.
   * @return Product specified by the given product ID, if found.
   */
  public Optional<Product> getProductFromExternalApi(long productId) {
    String productResourceUrl = String.format("https://redsky.target.com/v2/pdp/tcin/%d" +
            "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics", productId);

    ResponseEntity<ExternalProductWrapper> externalProductWrapper;
    try {
      externalProductWrapper = restTemplate.getForEntity(productResourceUrl, ExternalProductWrapper.class);
    } catch (HttpClientErrorException e) {
      log.error(String.format("Error occurred accessing external API [%s]: ", productResourceUrl));
      return Optional.empty();
    }

    if (externalProductWrapper.getStatusCode() == HttpStatus.OK) {
      Optional<ExternalProductDescription> productDescription = Optional.ofNullable(externalProductWrapper.getBody())
              .map(ExternalProductWrapper::getProduct)
              .map(ExternalProduct::getItem)
              .map(ExternalItem::getProductDescription);

      if (productDescription.isPresent()) {
        Product product = new Product();
        product.setId(productId);
        product.setName(productDescription.get().getTitle());
        return Optional.of(product);
      } else {
        log.error(String.format("Information retrieved from the external API [%s] was not in expected format.", productResourceUrl));
      }
    } else {
      log.error(String.format("HTTP response [%s] encountered when calling external API [%s].", externalProductWrapper.getStatusCode().toString(), productResourceUrl));
    }

    return Optional.empty();
  }
}
