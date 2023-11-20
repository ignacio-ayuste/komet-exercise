package com.unosof.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductCustomerDTO {

  private String productName;
  private String companyName;
  private BigDecimal price;
}
