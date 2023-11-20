package com.unosof.service;

import com.unosof.dto.CalculateProductsByCustomerResponse;
import com.unosof.entity.Customer;
import com.unosof.exception.EntityNotFoundException;
import com.unosof.mapper.ProductCustomerMapper;
import com.unosof.repository.CustomerRepository;
import com.unosof.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  public static final String CUSTOMER_WITH_ID_NOT_EXIST = "Customer with id: %s doesn't exist";

  private final InventoryRepository inventoryRepository;
  private final CustomerRepository customerRepository;
  private final ProductCustomerMapper productCustomerMapper;

  public CustomerService(InventoryRepository inventoryRepository,
      CustomerRepository customerRepository, ProductCustomerMapper productCustomerMapper) {
    this.inventoryRepository = inventoryRepository;
    this.customerRepository = customerRepository;
    this.productCustomerMapper = productCustomerMapper;
  }

  private Customer findCustomerById(Integer customerId) throws EntityNotFoundException {
    return customerRepository.findById(customerId).orElseThrow(
        () -> new EntityNotFoundException(String.format(CUSTOMER_WITH_ID_NOT_EXIST, customerId)));
  }

  public CalculateProductsByCustomerResponse calculateProductsByCustomer(Integer customerId)
      throws EntityNotFoundException {

    Customer customer = findCustomerById(customerId);
    CalculateProductsByCustomerResponse response = new CalculateProductsByCustomerResponse();

    inventoryRepository.findAll().stream()
        .map((i) -> productCustomerMapper.toDto(i, customer.getMarkdown()))
        .forEach(i -> response.getProducts().add(i));

    return response;
  }
}
