package com.unosof.mapper;

import com.unosof.dto.ProductCustomerDTO;
import com.unosof.entity.Inventory;
import com.unosof.util.ProductUtil;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ProductCustomerMapper {
  public ProductCustomerDTO toDto(Inventory inventory, BigDecimal markdown) {
    BigDecimal price = ProductUtil.calculatePrice(inventory, markdown);

    return new ProductCustomerDTO(inventory.getProduct().getName(),
        inventory.getCompany().getName(), price);
  }

}
