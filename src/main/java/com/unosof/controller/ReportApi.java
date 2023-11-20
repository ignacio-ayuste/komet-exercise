package com.unosof.controller;

import com.unosof.dto.CalculateProductsByCustomerResponse;
import com.unosof.dto.CompanyDTO;
import com.unosof.dto.ProductWithCodeDTO;
import com.unosof.exception.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ReportApi {

  @Operation(summary = "Fetch all products by company", description = "First Task of the exercise")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(path = "/company/{companyId}")
  CompanyDTO findByCompany(@PathVariable("companyId") Integer companyId)
      throws EntityNotFoundException;

  @Operation(summary = "Fetch all products by company", description = "Third Task of the exercise")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(path = "/company/{companyId}/products")
  List<ProductWithCodeDTO> findCompanyProducts(@PathVariable("companyId") Integer companyId)
      throws EntityNotFoundException;

  @Operation(summary = "Fetch all products calculating prices by customer markdown",
      description = "Second Task of the exercise")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(path = "/customer/{customerId}")
  CalculateProductsByCustomerResponse calculateProductsByCustomer(
      @PathVariable("customerId") Integer customerId) throws EntityNotFoundException;
}
