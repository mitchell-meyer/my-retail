package com.target.redsky.myretail.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class ExternalProductWrapper {

  private ExternalProduct product;
}
