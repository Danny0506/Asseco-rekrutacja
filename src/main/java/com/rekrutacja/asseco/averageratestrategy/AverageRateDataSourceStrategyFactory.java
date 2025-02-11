package com.rekrutacja.asseco.averageratestrategy;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AverageRateDataSourceStrategyFactory {

  private final Set<AverageRateDataSourceStrategy> strategies;

  public AverageRateDataSourceStrategy findStrategy(boolean isDataFromDatabase) {
    return strategies.stream()
        .filter(strategy -> strategy.shouldApply(isDataFromDatabase))
        .findFirst()
        .orElse(null);
  }
}
