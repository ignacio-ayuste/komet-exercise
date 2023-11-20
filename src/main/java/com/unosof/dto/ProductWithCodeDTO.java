package com.unosof.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductWithCodeDTO {

  private String productName;
  private String productCode;
}
