package com.rekrutacja.asseco.repository;

import com.rekrutacja.asseco.entity.SaleRateEntity;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRateRepository extends JpaRepository<SaleRateEntity, Long> {

  SaleRateEntity findByCodeAndDate(final String code, final LocalDate date);

  @Query("SELECT COUNT(*) FROM SaleRateEntity sr WHERE sr.code=?1 AND sr.date=?2")
  Long isCurrencyExistsByGivenCodeAndDate(final String code, final LocalDate date);
}
