package com.unosof.service;

import com.unosof.dto.CompanyDTO;
import com.unosof.dto.ProductWithCodeDTO;
import com.unosof.entity.Inventory;
import com.unosof.exception.EntityNotFoundException;
import com.unosof.mapper.ProductMapper;
import com.unosof.mapper.ProductWithCodeMapper;
import com.unosof.repository.InventoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
  public static final String COMPANY_WITH_ID_NOT_EXIST = "Company with id: %s doesn't exist";

  private final InventoryRepository inventoryRepository;
  private final ProductMapper productMapper;
  private final ProductWithCodeMapper productWithCodeMapper;

  public InventoryService(InventoryRepository inventoryRepository, ProductMapper productMapper,
      ProductWithCodeMapper productWithCodeMapper) {
    this.inventoryRepository = inventoryRepository;
    this.productMapper = productMapper;
    this.productWithCodeMapper = productWithCodeMapper;
  }

  private List<Inventory> findByCompany(Integer companyId) throws EntityNotFoundException {
    List<Inventory> inventories = inventoryRepository.findByCompanyId(companyId);
    if (inventories.isEmpty()) {
      throw new EntityNotFoundException(String.format(COMPANY_WITH_ID_NOT_EXIST, companyId));
    }
    return inventories;
  }

  public CompanyDTO findItemsByCompany(Integer companyId) throws EntityNotFoundException {
    CompanyDTO companyDTO = new CompanyDTO(companyId);
    findByCompany(companyId).stream().map(productMapper::toDto).forEach(companyDTO::addProduct);

    return companyDTO;
  }

  public List<ProductWithCodeDTO> findProductsByCompany(Integer companyId)
      throws EntityNotFoundException {

    return findByCompany(companyId).stream().map(productWithCodeMapper::toDto)
        .collect(Collectors.toList());
  }
}
