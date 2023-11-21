package com.unosof.mapper;

import com.unosof.dto.ProductDTO;
import com.unosof.entity.Inventory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import static com.unosof.DataHelper.createInventory;
import static com.unosof.util.ProductUtil.calculateCubesPerBox;
import static com.unosof.util.ProductUtil.calculateFinalFreight;
import static com.unosof.util.ProductUtil.calculateOutboundFreight;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

  @Test
  public void testToDto() {
    Inventory inventory = createInventory(BigDecimal.TEN, BigDecimal.TEN);

    BigDecimal cubesPerBox = calculateCubesPerBox(inventory);
    BigDecimal outboundFreight = calculateOutboundFreight(inventory, cubesPerBox);
    BigDecimal finalFreight = calculateFinalFreight(inventory, outboundFreight);

    ProductMapper mapper = new ProductMapper();

    ProductDTO resultDTO = mapper.toDto(inventory);

    assertEquals("ExampleProduct", resultDTO.getProductName());
    assertEquals(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP), resultDTO.getBasePrice());
    assertEquals(finalFreight, resultDTO.getFinalFreight());
  }
}
