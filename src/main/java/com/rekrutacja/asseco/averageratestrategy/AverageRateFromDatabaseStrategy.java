package com.rekrutacja.asseco.averageratestrategy;

import com.rekrutacja.asseco.dto.AverageRateResponse;
import com.rekrutacja.asseco.entity.AverageRateEntity;
import com.rekrutacja.asseco.repository.AverageRateRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AverageRateFromDatabaseStrategy implements AverageRateDataSourceStrategy {

  private final AverageRateRepository averageRateRepository;

  @Override
  public boolean shouldApply(boolean isDataFromDatabase) {
    return isDataFromDatabase;
  }

  @Override
  public AverageRateResponse getData(final String code, final LocalDate date, BigDecimal amount) {
    final AverageRateEntity averageRateEntity = averageRateRepository.findByCodeAndDate(code, date);
    return mapToAverageRateResponse(averageRateEntity, amount);
  }

  private AverageRateResponse mapToAverageRateResponse(
      final AverageRateEntity averageRateEntity, final BigDecimal amount) {
    return new AverageRateResponse(
        averageRateEntity.getMid().multiply(amount), averageRateEntity.getCurrencyName());
  }
}
