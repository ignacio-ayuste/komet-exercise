package com.unosof.mapper;

import com.unosof.dto.ProductDTO;
import com.unosof.entity.Inventory;
import com.unosof.util.ProductUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;
import static com.unosof.util.ProductUtil.DECIMAL_SCALE;

@Component
public class ProductMapper {
  public ProductDTO toDto(Inventory inventory) {
    BigDecimal cubesPerBox = ProductUtil.calculateCubesPerBox(inventory);
    BigDecimal outboundFreight = ProductUtil.calculateOutboundFreight(inventory, cubesPerBox);
    BigDecimal finalFreight = ProductUtil.calculateFinalFreight(inventory, outboundFreight);

    return new ProductDTO(inventory.getProduct().getName(),
        inventory.getBasePrice().setScale(DECIMAL_SCALE, RoundingMode.HALF_UP), finalFreight);
  }
}
