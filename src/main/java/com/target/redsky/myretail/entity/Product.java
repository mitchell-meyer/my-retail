package com.target.redsky.myretail.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Product implements Serializable {

  private long id;

  private String name;

  private BigDecimal currentPrice;

  private String currencyCode;
}
