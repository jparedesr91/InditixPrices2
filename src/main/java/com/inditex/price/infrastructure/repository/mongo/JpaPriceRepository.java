package com.inditex.price.infrastructure.repository.mongo;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

  @Query(value = """
      SELECT P.*
      FROM PRICES P
      WHERE PRODUCT_ID = ?1
      AND BRAND_ID = ?2
      AND ?3 BETWEEN START_DATE AND END_DATE
      ORDER BY PRIORITY DESC
      FETCH FIRST ROW ONLY
      """, nativeQuery = true)
  PriceEntity findByProductIdBrandIdAndApplicationDate(Long productId, Long brandId,
      LocalDateTime applicationDate);

}
