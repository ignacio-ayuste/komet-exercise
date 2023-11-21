package com.unosof.mapper;

import com.unosof.dto.ProductCustomerDTO;
import com.unosof.entity.Inventory;
import com.unosof.util.ProductUtil;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static com.unosof.DataHelper.createInventory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCustomerMapperTest {

  @Test
  public void testToDto() {
    Inventory inventory = createInventory(BigDecimal.TEN, BigDecimal.TEN);

    BigDecimal markdown = BigDecimal.valueOf(20);
    BigDecimal calculatedPrice = ProductUtil.calculatePrice(inventory, markdown);
    ProductCustomerMapper mapper = new ProductCustomerMapper();
    ProductCustomerDTO resultDTO = mapper.toDto(inventory, markdown);

    assertEquals("ExampleProduct", resultDTO.getProductName());
    assertEquals("ExampleCompany", resultDTO.getCompanyName());
    assertEquals(calculatedPrice, resultDTO.getPrice());
  }
}
