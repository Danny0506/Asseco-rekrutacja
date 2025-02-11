package com.rekrutacja.asseco.saleratestrategy;

import com.rekrutacja.asseco.dto.SaleRateResponse;
import com.rekrutacja.asseco.entity.SaleRateEntity;
import com.rekrutacja.asseco.repository.SaleRateRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleRateFromDatabaseStrategy implements SaleRateDataSourceStrategy {

  private final SaleRateRepository saleRateRepository;

  @Override
  public boolean shouldApply(boolean isDataFromDatabase) {
    return isDataFromDatabase;
  }

  @Override
  public SaleRateResponse getData(String code, LocalDate date) {
    final SaleRateEntity saleRateEntity = saleRateRepository.findByCodeAndDate(code, date);
    return mapToSaleRateResponse(saleRateEntity);
  }

  private SaleRateResponse mapToSaleRateResponse(final SaleRateEntity saleRateEntity) {
    final SaleRateResponse saleRateResponse = new SaleRateResponse();
    saleRateResponse.setAsk(saleRateEntity.getAsk());
    saleRateResponse.setBid(saleRateEntity.getBid());
    saleRateResponse.setCurrencyName(saleRateEntity.getCurrencyName());
    saleRateResponse.setCurrencyCode(saleRateEntity.getCode());

    return saleRateResponse;
  }
}
