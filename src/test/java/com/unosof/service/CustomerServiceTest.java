package com.unosof.service;

import com.unosof.dto.CalculateProductsByCustomerResponse;
import com.unosof.dto.ProductCustomerDTO;
import com.unosof.entity.Customer;
import com.unosof.entity.Inventory;
import com.unosof.exception.EntityNotFoundException;
import com.unosof.mapper.ProductCustomerMapper;
import com.unosof.repository.CustomerRepository;
import com.unosof.repository.InventoryRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static com.unosof.DataHelper.createInventory;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

  @Autowired
  private CustomerService customerService;

  @MockBean
  private InventoryRepository inventoryRepository;

  @MockBean
  private CustomerRepository customerRepository;

  @MockBean
  private ProductCustomerMapper productCustomerMapper;

  @Test
  public void testCalculateProductsByCustomer() throws EntityNotFoundException {
    Integer customerId = 1;
    Customer customer = new Customer(customerId, "customerName", BigDecimal.ONE);

    when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

    List<Inventory> inventories = new ArrayList<>();
    Inventory inventory = createInventory(BigDecimal.TEN, BigDecimal.TEN);
    inventories.add(inventory);

    when(productCustomerMapper.toDto(any(Inventory.class), any(BigDecimal.class))).thenReturn(
        new ProductCustomerDTO("productName", "companyName", BigDecimal.ONE));

    when(inventoryRepository.findAll()).thenReturn(inventories);

    CalculateProductsByCustomerResponse response =
        customerService.calculateProductsByCustomer(customerId);

    verify(customerRepository, times(1)).findById(customerId);
    verify(productCustomerMapper, times(inventories.size())).toDto(any(Inventory.class),
        any(BigDecimal.class));

    assertNotNull(response);
    assertNotNull(response.getProducts());
  }

  @Test
  public void testCalculateProductsByCustomer_EntityNotFoundException() {
    Integer customerId = 1;

    when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> {
      customerService.calculateProductsByCustomer(customerId);
    });

    verify(customerRepository, times(1)).findById(customerId);
    verifyNoInteractions(productCustomerMapper);
  }

}
