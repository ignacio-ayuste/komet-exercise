package com.unosof.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
@Table(name = "tblboxtypept")
public class BoxType {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private Integer id;

  @Basic
  @Column(name = "code")
  private String code;

  @Basic
  @Column(name = "width")
  private BigDecimal width;

  @Basic
  @Column(name = "height")
  private BigDecimal height;

  @Basic
  @Column(name = "length")
  private BigDecimal length;

}
