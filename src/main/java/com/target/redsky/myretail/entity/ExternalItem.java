package com.target.redsky.myretail.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalItem {

  @JsonProperty("tcin")
  private long productId;

  @JsonProperty("product_description")
  private ExternalProductDescription productDescription;
}
