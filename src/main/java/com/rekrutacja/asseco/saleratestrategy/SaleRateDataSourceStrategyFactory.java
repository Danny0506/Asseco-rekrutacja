package com.rekrutacja.asseco.saleratestrategy;

import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleRateDataSourceStrategyFactory {
  private final Set<SaleRateDataSourceStrategy> strategies;

  public SaleRateDataSourceStrategy findStrategy(boolean isDataFromDatabase) {
    return strategies.stream()
        .filter(strategy -> strategy.shouldApply(isDataFromDatabase))
        .findFirst()
        .orElse(null);
  }
}
