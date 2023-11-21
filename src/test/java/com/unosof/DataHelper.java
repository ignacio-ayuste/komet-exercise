package com.unosof;


import com.unosof.entity.BoxType;
import com.unosof.entity.Company;
import com.unosof.entity.Inventory;
import com.unosof.entity.Product;
import java.math.BigDecimal;

public class DataHelper {
  public static Inventory createInventory(BigDecimal basePrice, BigDecimal cubesPerCarrier) {
    BoxType boxType = new BoxType(1, "boxType", BigDecimal.valueOf(30), BigDecimal.valueOf(40),
        BigDecimal.valueOf(50));
    Company company = new Company(1, "ExampleCompany");
    Product product = new Product(1, "ExampleProduct", BigDecimal.ONE);

    return new Inventory(1, cubesPerCarrier, 5, basePrice, boxType, product, company);
  }

}
