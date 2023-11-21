package com.unosof.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {

  private String productName;
  private BigDecimal basePrice;
  private BigDecimal finalFreight;
}
