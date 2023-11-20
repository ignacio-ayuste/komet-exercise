package com.unosof.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CalculateProductsByCustomerResponse {

  private List<ProductCustomerDTO> products;

  public CalculateProductsByCustomerResponse() {
    this.products = new ArrayList<>();
  }

  public void addProduct(ProductCustomerDTO productCustomerDTO) {
    products.add(productCustomerDTO);
  }
}