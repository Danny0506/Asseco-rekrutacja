package com.rekrutacja.asseco.saleratestrategy;

import com.rekrutacja.asseco.dto.SaleRateResponse;
import java.time.LocalDate;

public interface SaleRateDataSourceStrategy {
  boolean shouldApply(final boolean isDataFromDatabase);

  SaleRateResponse getData(final String code, final LocalDate date);
}
