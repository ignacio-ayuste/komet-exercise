package com.unosof.mapper;

import com.unosof.dto.ProductWithCodeDTO;
import com.unosof.entity.Inventory;
import com.unosof.entity.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static com.unosof.util.ProductUtil.generateProductCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductWithCodeMapperTest {

  @Test
  public void testToDto() {
    Inventory inventory = new Inventory();
    Product product = new Product(1, "ExampleProduct", BigDecimal.TEN);
    String productName = "ExampleProduct";
    inventory.setProduct(product);

    ProductWithCodeMapper mapper = new ProductWithCodeMapper();
    ProductWithCodeDTO resultDTO = mapper.toDto(inventory);

    assertEquals(productName, resultDTO.getProductName());
    assertEquals(generateProductCode(productName), resultDTO.getProductCode());
  }

}
