package com.unosof.mapper;

import com.unosof.dto.ProductWithCodeDTO;
import com.unosof.entity.Inventory;
import com.unosof.util.ProductUtil;
import org.springframework.stereotype.Component;

@Component
public class ProductWithCodeMapper {
  public ProductWithCodeDTO toDto(Inventory inventory) {
    return new ProductWithCodeDTO(inventory.getProduct().getName(),
        ProductUtil.generateProductCode(inventory.getProduct().getName()));
  }
}
