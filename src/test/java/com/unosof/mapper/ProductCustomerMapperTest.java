package com.unosof.mapper;

import com.unosof.dto.ProductCustomerDTO;
import com.unosof.entity.Company;
import com.unosof.entity.Inventory;
import com.unosof.entity.Product;
import com.unosof.util.ProductUtil;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCustomerMapperTest {

  @Test
  public void testToDto() {
    Inventory inventory = new Inventory();
    Product product = new Product();
    product.setName("ExampleProduct");
    inventory.setProduct(product);

    Company company = new Company();
    company.setName("ExampleCompany");
    inventory.setCompany(company);

    BigDecimal basePrice = BigDecimal.valueOf(100);
    inventory.setBasePrice(basePrice);

    BigDecimal markdown = BigDecimal.valueOf(20);
    BigDecimal calculatedPrice = ProductUtil.calculatePrice(inventory, markdown);
    ProductCustomerMapper mapper = new ProductCustomerMapper();
    ProductCustomerDTO resultDTO = mapper.toDto(inventory, markdown);

    assertEquals("ExampleProduct", resultDTO.getProductName());
    assertEquals("ExampleCompany", resultDTO.getCompanyName());
    assertEquals(calculatedPrice, resultDTO.getPrice());
  }
}
