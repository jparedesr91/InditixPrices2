package com.inditex.price.infrastructure.driven.database.repositories;

import java.time.LocalDateTime;

import com.inditex.price.infrastructure.driven.database.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

  @Query(value = """
      SELECT p
      FROM PriceEntity p
      WHERE p.product.id = ?1
      AND p.brand.id = ?2
      AND ?3 BETWEEN p.startDate AND p.endDate
      ORDER BY p.priority DESC
      LIMIT 1
      """)
  PriceEntity findByProductIdBrandIdAndApplicationDate(Long productId, Long brandId,
      LocalDateTime applicationDate);

}
