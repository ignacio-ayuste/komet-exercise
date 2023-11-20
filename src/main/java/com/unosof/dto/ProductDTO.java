package com.unosof.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

  private String productName;
  private BigDecimal basePrice;
  private BigDecimal finalFreight;
}
