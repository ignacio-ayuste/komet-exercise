package com.unosof.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCustomerDTO {

  private String productName;
  private String companyName;
  private BigDecimal price;
}
