package com.unosof.service;

import com.unosof.dto.CompanyDTO;
import com.unosof.dto.ProductDTO;
import com.unosof.dto.ProductWithCodeDTO;
import com.unosof.entity.Inventory;
import com.unosof.exception.EntityNotFoundException;
import com.unosof.mapper.ProductMapper;
import com.unosof.mapper.ProductWithCodeMapper;
import com.unosof.repository.InventoryRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InventoryServiceTest {

  private final int COMPANY_ID = 1;
  @Autowired
  private InventoryService inventoryService;
  @MockBean
  private InventoryRepository inventoryRepository;
  @MockBean
  private ProductMapper productMapper;
  @MockBean
  private ProductWithCodeMapper productWithCodeMapper;

  @Test
  public void testFindItemsByCompany() throws EntityNotFoundException {

    List<Inventory> inventories = new ArrayList<>();
    inventories.add(new Inventory());

    when(productMapper.toDto(any(Inventory.class))).thenReturn(
        new ProductDTO("productName", BigDecimal.ONE, BigDecimal.TEN));
    when(inventoryRepository.findByCompanyId(COMPANY_ID)).thenReturn(inventories);

    CompanyDTO resultDTO = inventoryService.findItemsByCompany(COMPANY_ID);

    verify(inventoryRepository, times(1)).findByCompanyId(COMPANY_ID);
    verify(productMapper, times(inventories.size())).toDto(any(Inventory.class));

    assertNotNull(resultDTO);
  }

  @Test
  public void testFindProductsByCompany() throws EntityNotFoundException {
    List<Inventory> inventories = new ArrayList<>();
    inventories.add(new Inventory());

    when(productWithCodeMapper.toDto(any(Inventory.class))).thenReturn(
        new ProductWithCodeDTO("productName", "code"));
    when(inventoryRepository.findByCompanyId(COMPANY_ID)).thenReturn(inventories);

    List<ProductWithCodeDTO> resultDTOs = inventoryService.findProductsByCompany(COMPANY_ID);

    verify(inventoryRepository, times(1)).findByCompanyId(COMPANY_ID);
    verify(productWithCodeMapper, times(inventories.size())).toDto(any(Inventory.class));

    assertNotNull(resultDTOs);
  }
}
