package com.unosof.util;

import com.unosof.entity.Inventory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.unosof.DataHelper.createInventory;
import static com.unosof.util.ProductUtil.DECIMAL_SCALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductUtilTest {

  @Test
  public void testCalculatePrice() {
    Inventory inventory = createInventory(BigDecimal.valueOf(100), BigDecimal.TEN);
    BigDecimal markdown = BigDecimal.valueOf(20);

    BigDecimal expected = BigDecimal.valueOf(80.00).setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
    BigDecimal result = ProductUtil.calculatePrice(inventory, markdown);

    assertEquals(expected, result, "Calculation for price is incorrect");
  }

  @Test
  public void testCalculateCubesPerBox() {
    Inventory inventory = createInventory(BigDecimal.TEN, BigDecimal.TEN);

    BigDecimal expected = BigDecimal.valueOf(34.72);
    BigDecimal result = ProductUtil.calculateCubesPerBox(inventory);

    assertEquals(expected, result, "Calculation for cubes per box is incorrect");
  }

  @Test
  public void testCalculateOutboundFreight() {
    Inventory inventory = createInventory(BigDecimal.valueOf(100), BigDecimal.TEN);

    BigDecimal cubesPerBox = BigDecimal.valueOf(20);
    BigDecimal expected = BigDecimal.valueOf(40.00).setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
    BigDecimal result = ProductUtil.calculateOutboundFreight(inventory, cubesPerBox);

    assertEquals(expected, result, "Calculation for outbound freight is incorrect");
  }

  @Test
  public void testCalculateFinalFreight() {
    Inventory inventory = createInventory(BigDecimal.TEN, BigDecimal.valueOf(5));

    BigDecimal outboundFreight = BigDecimal.valueOf(500);
    BigDecimal expected = BigDecimal.valueOf(5).setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
    BigDecimal result = ProductUtil.calculateFinalFreight(inventory, outboundFreight);

    assertEquals(expected, result, "Calculation for final freight is incorrect");
  }

  @ParameterizedTest
  @CsvSource({"Red Roses 23cm, R1d-R3s-22m", "IL Hydrangea Blue, I0L-H7a-B2e",
      "Black Gira%sol 17Inch, B3k-G6%l-14h", "&White pom 3Inch, &4&e-p1m-33h"})
  public void testGenerateProductCode(String productName, String expectedProductCode) {
    String actualProductCode = ProductUtil.generateProductCode(productName);
    assertEquals(expectedProductCode, actualProductCode);
  }

}
