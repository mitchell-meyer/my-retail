package com.target.redsky.myretail.service

import com.target.redsky.myretail.entity.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

class ExternalProductServiceSpec extends Specification {

  private ExternalProductService externalProductService

  private def restTemplate = Mock(RestTemplate)

  def setup() {
    externalProductService = new ExternalProductService(restTemplate)
  }

  def "Product information from external service should be translated to internal service object and returned."() {
    given:
    restTemplate.getForEntity(_ as String, ExternalProductWrapper.class) >> getMockedResponseEntity(HttpStatus.OK)

    when:
    Optional<Product> optionalProduct = externalProductService.getProductFromExternalApi(1)

    then:
    optionalProduct.isPresent()
    optionalProduct.get().getId() == 1
    optionalProduct.get().getName() == "Product Name"
  }

  @Unroll
  def "External service should behave appropriately for [HttpStatus] code of [#returnedHttpStatus]."() {
    given:
    restTemplate.getForEntity(_ as String, ExternalProductWrapper.class) >> getMockedResponseEntity(returnedHttpStatus as HttpStatus)

    when:
    Optional<Product> optionalProduct = externalProductService.getProductFromExternalApi(1)

    then:
    if (returnedHttpStatus == HttpStatus.OK) {
      optionalProduct.isPresent()
    } else {
      optionalProduct.isEmpty()
    }

    where:
    returnedHttpStatus << HttpStatus.values()
  }

  /**
   * Returns a mocked {@link ResponseEntity} object from an external API request.
   *
   * @param httpStatus Status code that the response should emulate.
   * @return Mocked {@link ResponseEntity} object.
   */
  private ResponseEntity<ExternalProductWrapper> getMockedResponseEntity(HttpStatus httpStatus) {
    ResponseEntity<ExternalProductWrapper> externalProductWrapper = Mock() {
      getStatusCode() >> httpStatus
      getBody() >> new ExternalProductWrapper(
              product: new ExternalProduct(
                      item: new ExternalItem(
                              productDescription: new ExternalProductDescription(
                                      title: "Product Name"
                              )
                      )
              )
      )
    }
    return externalProductWrapper
  }
}
