package com.unosof.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductWithCodeDTO {

  private String productName;
  private String productCode;
}
