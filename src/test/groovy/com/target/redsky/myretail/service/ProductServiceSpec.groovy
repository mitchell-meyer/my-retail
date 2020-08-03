package com.target.redsky.myretail.service

import com.target.redsky.myretail.entity.Price
import com.target.redsky.myretail.entity.Product
import com.target.redsky.myretail.repository.PriceRepository
import spock.lang.Specification

class ProductServiceSpec extends Specification {

  ProductService productService

  def externalProductService = Mock(ExternalProductService)
  def priceRepository = Mock(PriceRepository)

  def setup() {
    productService = new ProductService(externalProductService, priceRepository)
  }

  void "Retrieving product and pricing information should be compiled properly into a single return object."() {
    given:
    long productId = 1

    and:
    Product product = new Product(id: productId, name: "Product Name")
    externalProductService.getProductFromExternalApi(productId) >> Optional.of(product)

    and:
    Price price = new Price(currentPrice: 99.99, currencyCode: "USD")
    priceRepository.findById(productId) >> Optional.of(price)

    when:
    Product returnedProduct = productService.getProduct(productId)

    then:
    returnedProduct.id == 1
    returnedProduct.name == "Product Name"
    returnedProduct.currentPrice == 99.99
    returnedProduct.currencyCode == "USD"
  }
}
