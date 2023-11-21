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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tblinventorypt")
public class Inventory {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private Integer id;

  @Basic
  @Column(name = "cubesPerCarrier")
  @Getter
  private BigDecimal cubesPerCarrier;

  @Basic
  @Column(name = "pack")
  @Getter
  private Integer pack;

  @Basic
  @Column(name = "basePrice")
  @Getter
  private BigDecimal basePrice;

  @ManyToOne
  @JoinColumn(name = "boxTypeId", referencedColumnName = "id", nullable = false)
  @Getter
  private BoxType boxType;

  @ManyToOne
  @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
  @Getter
  private Product product;

  @ManyToOne
  @JoinColumn(name = "companyId", referencedColumnName = "id", nullable = false)
  @Getter
  private Company company;

}
