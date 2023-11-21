package com.unosof.mapper;

import com.unosof.dto.ProductWithCodeDTO;
import com.unosof.entity.Inventory;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static com.unosof.DataHelper.createInventory;
import static com.unosof.util.ProductUtil.generateProductCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductWithCodeMapperTest {

  @Test
  public void testToDto() {
    Inventory inventory = createInventory(BigDecimal.TEN, BigDecimal.TEN);
    String productName = "ExampleProduct";

    ProductWithCodeMapper mapper = new ProductWithCodeMapper();
    ProductWithCodeDTO resultDTO = mapper.toDto(inventory);

    assertEquals(productName, resultDTO.getProductName());
    assertEquals(generateProductCode(productName), resultDTO.getProductCode());
  }

}
