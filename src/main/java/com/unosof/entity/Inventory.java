package com.unosof.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "tblinventorypt")
public class Inventory {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private Integer id;

  @Basic
  @Column(name = "cubesPerCarrier")
  private BigDecimal cubesPerCarrier;

  @Basic
  @Column(name = "pack")
  private Integer pack;

  @Basic
  @Column(name = "basePrice")
  private BigDecimal basePrice;

  @ManyToOne
  @JoinColumn(name = "boxTypeId", referencedColumnName = "id", nullable = false)
  private BoxType boxType;

  @ManyToOne
  @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "companyId", referencedColumnName = "id", nullable = false)
  private Company company;

}
