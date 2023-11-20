package com.unosof.repository;


import com.unosof.entity.Inventory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
  List<Inventory> findByCompanyId(Integer companyId);

}
