package com.unosof.mapper;

import com.unosof.dto.ProductDTO;
import com.unosof.entity.BoxType;
import com.unosof.entity.Inventory;
import com.unosof.entity.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import static com.unosof.util.ProductUtil.DECIMAL_SCALE;
import static com.unosof.util.ProductUtil.calculateCubesPerBox;
import static com.unosof.util.ProductUtil.calculateFinalFreight;
import static com.unosof.util.ProductUtil.calculateOutboundFreight;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

  @Test
  public void testToDto() {
    Inventory inventory = new Inventory();
    Product product = new Product(1, "ExampleProduct", BigDecimal.TEN);
    inventory.setProduct(product);

    BigDecimal basePrice = BigDecimal.valueOf(100);
    inventory.setBasePrice(basePrice);
    inventory.setCubesPerCarrier(BigDecimal.valueOf(10));
    inventory.setPack(10);

    BoxType boxType = new BoxType(1, "boxType", BigDecimal.valueOf(30), BigDecimal.valueOf(40),
        BigDecimal.valueOf(50));
    inventory.setBoxType(boxType);

    BigDecimal cubesPerBox = calculateCubesPerBox(inventory);
    BigDecimal outboundFreight = calculateOutboundFreight(inventory, cubesPerBox);
    BigDecimal finalFreight = calculateFinalFreight(inventory, outboundFreight);

    ProductMapper mapper = new ProductMapper();

    ProductDTO resultDTO = mapper.toDto(inventory);

    assertEquals("ExampleProduct", resultDTO.getProductName());
    assertEquals(basePrice.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP), resultDTO.getBasePrice());
    assertEquals(finalFreight, resultDTO.getFinalFreight());
  }
}
