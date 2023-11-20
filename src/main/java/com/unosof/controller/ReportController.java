package com.unosof.controller;

import com.unosof.dto.CalculateProductsByCustomerResponse;
import com.unosof.dto.CompanyDTO;
import com.unosof.dto.ProductWithCodeDTO;
import com.unosof.exception.EntityNotFoundException;
import com.unosof.service.CustomerService;
import com.unosof.service.InventoryService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "report")
public class ReportController implements ReportApi {
  public static final Logger LOG = LoggerFactory.getLogger(ReportController.class);
  private final InventoryService inventoryService;
  private final CustomerService customerService;

  public ReportController(InventoryService inventoryService, CustomerService customerService) {
    this.inventoryService = inventoryService;
    this.customerService = customerService;
  }

  @Override
  @GetMapping(path = "/company/{companyId}")
  public CompanyDTO findByCompany(@PathVariable("companyId") Integer companyId)
      throws EntityNotFoundException {
    LOG.info("findByCompany, with companyId: {}", companyId);
    return inventoryService.findItemsByCompany(companyId);
  }

  @Override
  @GetMapping(path = "/company/{companyId}/products")
  public List<ProductWithCodeDTO> findCompanyProducts(@PathVariable("companyId") Integer companyId)
      throws EntityNotFoundException {
    LOG.info("findCompanyProducts, with companyId: {}", companyId);
    return inventoryService.findProductsByCompany(companyId);
  }

  @Override
  @GetMapping(path = "/customer/{customerId}")
  public CalculateProductsByCustomerResponse calculateProductsByCustomer(
      @PathVariable("customerId") Integer customerId) throws EntityNotFoundException {
    LOG.info("calculateProductsByCustomer, with customerId: {}", customerId);
    return customerService.calculateProductsByCustomer(customerId);
  }
}
