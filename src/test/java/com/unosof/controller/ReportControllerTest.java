package com.unosof.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unosof.dto.CalculateProductsByCustomerResponse;
import com.unosof.dto.CompanyDTO;
import com.unosof.dto.ProductCustomerDTO;
import com.unosof.dto.ProductDTO;
import com.unosof.dto.ProductWithCodeDTO;
import com.unosof.exception.EntityNotFoundException;
import com.unosof.model.ApiError;
import com.unosof.service.CustomerService;
import com.unosof.service.InventoryService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static com.unosof.service.CustomerService.CUSTOMER_WITH_ID_NOT_EXIST;
import static com.unosof.service.InventoryService.COMPANY_WITH_ID_NOT_EXIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

  private static final String BASE_URL = "/report";
  private static final String CUSTOMER_ENDPOINT = BASE_URL + "/customer/%d";
  private static final String COMPANY_ENDPOINT = BASE_URL + "/company/%d";
  private static final String COMPANY_PRODUCTS_ENDPOINT = BASE_URL + "/company/%d/products";
  private final int COMPANY_ID = 1;
  private final int CUSTOMER_ID = 1;
  private final String PRODUCT_NAME = "productNameTest";

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private InventoryService inventoryService;
  @MockBean
  private CustomerService customerService;
  @Autowired
  private ObjectMapper mapper;

  @Test
  public void testFindByCompany() throws Exception {
    CompanyDTO companyDTO = new CompanyDTO(COMPANY_ID);
    ProductDTO productDTO =
        new ProductDTO(PRODUCT_NAME, BigDecimal.valueOf(1), BigDecimal.valueOf(1));
    companyDTO.addProduct(productDTO);
    when(inventoryService.findItemsByCompany(anyInt())).thenReturn(companyDTO);

    CompanyDTO response =
        (CompanyDTO) doCall(String.format(COMPANY_ENDPOINT, COMPANY_ID), CompanyDTO.class);

    assertThat(response.getCompanyId()).isEqualTo(COMPANY_ID);
    assertThat(response.getProducts()).hasSize(1).first()
        .hasFieldOrPropertyWithValue("productName", PRODUCT_NAME)
        .hasFieldOrPropertyWithValue("basePrice", BigDecimal.valueOf(1))
        .hasFieldOrPropertyWithValue("finalFreight", BigDecimal.valueOf(1));
  }

  @Test
  public void testFindCompanyProducts() throws Exception {
    List<ProductWithCodeDTO> mockResponse = new ArrayList<>();
    ProductWithCodeDTO productWithCodeDTO = new ProductWithCodeDTO(PRODUCT_NAME, "code");
    mockResponse.add(productWithCodeDTO);
    when(inventoryService.findProductsByCompany(anyInt())).thenReturn(mockResponse);

    List<?> response =
        (List<?>) doCall(String.format(COMPANY_PRODUCTS_ENDPOINT, COMPANY_ID),
            List.class);

    assertThat(response).hasSize(1).first().hasFieldOrPropertyWithValue("productName", PRODUCT_NAME)
        .hasFieldOrPropertyWithValue("productCode", "code");
  }

  @Test
  public void testCalculateProductsByCustomer() throws Exception {
    CalculateProductsByCustomerResponse mockResponse = new CalculateProductsByCustomerResponse();
    String COMPANY_NAME = "companyNameTest";
    mockResponse.addProduct(
        new ProductCustomerDTO(PRODUCT_NAME, COMPANY_NAME, BigDecimal.valueOf(1)));
    when(customerService.calculateProductsByCustomer(anyInt())).thenReturn(mockResponse);

    CalculateProductsByCustomerResponse response =
        (CalculateProductsByCustomerResponse) doCall(String.format(CUSTOMER_ENDPOINT, CUSTOMER_ID),
            CalculateProductsByCustomerResponse.class);

    assertThat(response.getProducts()).hasSize(1).first()
        .hasFieldOrPropertyWithValue("productName", PRODUCT_NAME)
        .hasFieldOrPropertyWithValue("companyName", COMPANY_NAME)
        .hasFieldOrPropertyWithValue("price", BigDecimal.valueOf(1));
  }

  @Test
  public void testFindByCompany_EntityNotFoundException() throws Exception {
    String errorMessage = String.format(COMPANY_WITH_ID_NOT_EXIST, COMPANY_ID);
    doThrow(new EntityNotFoundException(errorMessage)).when(inventoryService)
        .findItemsByCompany(anyInt());
    doFailedCall(String.format(COMPANY_ENDPOINT, COMPANY_ID), errorMessage);
  }

  @Test
  public void testFindCompanyProducts_EntityNotFoundException() throws Exception {
    String errorMessage = String.format(COMPANY_WITH_ID_NOT_EXIST, COMPANY_ID);
    doThrow(new EntityNotFoundException(errorMessage)).when(inventoryService)
        .findProductsByCompany(anyInt());
    doFailedCall(String.format(COMPANY_PRODUCTS_ENDPOINT, COMPANY_ID), errorMessage);
  }

  @Test
  public void testCalculateProductsByCustomer_EntityNotFoundException() throws Exception {
    String errorMessage = String.format(CUSTOMER_WITH_ID_NOT_EXIST, CUSTOMER_ID);
    doThrow(new EntityNotFoundException(errorMessage)).when(customerService)
        .calculateProductsByCustomer(anyInt());
    doFailedCall(String.format(CUSTOMER_ENDPOINT, CUSTOMER_ID), errorMessage);
  }

  private Object doCall(String endpoint, Class<?> returnClass) {
    Object response;
    try {
      MockHttpServletResponse result =
          mockMvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON)).andReturn()
              .getResponse();

      response = mapper.readValue(result.getContentAsString(), returnClass);
    } catch (Exception e) {
      throw new RuntimeException("Error doing call {}", e);
    }
    return response;
  }

  private void doFailedCall(String endpoint, String errorMessage) {
    try {
      ApiError error = (ApiError) doCall(endpoint, ApiError.class);

      assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
      assertEquals(1, error.getErrors().size());
      assertEquals(error.getMessage(), errorMessage);
      assertTrue(error.getErrors().get(0).contains("error occurred"));
    } catch (Exception e) {
      throw new RuntimeException("Error doing call {}", e);
    }
  }
}
