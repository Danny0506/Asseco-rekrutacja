package com.rekrutacja.asseco.repository;

import com.rekrutacja.asseco.entity.AverageRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AverageRateRepository extends JpaRepository<AverageRateEntity, Long> {

    AverageRateEntity findByCodeAndDate(final String code, final LocalDate date);

    @Query("SELECT COUNT(*) FROM AverageRateEntity av WHERE av.code=?1 AND av.date=?2")
    Long isCurrencyExistsByGivenCodeAndDate(final String code, final LocalDate date);
}
