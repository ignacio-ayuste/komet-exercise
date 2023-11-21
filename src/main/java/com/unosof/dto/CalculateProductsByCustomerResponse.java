package com.unosof.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CalculateProductsByCustomerResponse {

  private final List<ProductCustomerDTO> products;

  public CalculateProductsByCustomerResponse() {
    this.products = new ArrayList<>();
  }

  public void addProduct(ProductCustomerDTO productCustomerDTO) {
    products.add(productCustomerDTO);
  }
}
