package com.inditex.price.infrastructure.driven.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRICES")
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false, updatable = false)
  private ProductEntity product;
  @ManyToOne
  @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", nullable = false, updatable = false)
  private BrandEntity brand;
  @Column(name = "START_DATE")
  private LocalDateTime startDate;
  @Column(name = "END_DATE")
  private LocalDateTime endDate;
  @Column(name = "PRIORITY")
  private int priority;
  @Column(name = "PRICE")
  private Double price;
  @Column(name = "CURR")
  private String currency;

}

