package com.unosof.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;


@Getter
public class CompanyDTO implements Serializable {

  private final int companyId;
  private final List<ProductDTO> products;

  @JsonCreator
  public CompanyDTO(int companyId) {
    this.companyId = companyId;
    this.products = new ArrayList<>();
  }

  public void addProduct(ProductDTO product) {
    products.add(product);
  }
}
