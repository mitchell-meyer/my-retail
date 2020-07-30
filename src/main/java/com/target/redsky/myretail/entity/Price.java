package com.target.redsky.myretail.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
public class Price {

  @Id
  private long id;

  private BigDecimal currentPrice;

  private String currencyCode;
}
