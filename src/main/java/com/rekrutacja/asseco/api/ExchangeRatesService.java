package com.rekrutacja.asseco.api;

import com.rekrutacja.asseco.averageratestrategy.AverageRateDataSourceStrategy;
import com.rekrutacja.asseco.averageratestrategy.AverageRateDataSourceStrategyFactory;
import com.rekrutacja.asseco.dto.AverageRateRequest;
import com.rekrutacja.asseco.dto.AverageRateResponse;
import com.rekrutacja.asseco.dto.CurrencyRequest;
import com.rekrutacja.asseco.dto.SaleRateResponse;
import com.rekrutacja.asseco.repository.AverageRateRepository;
import com.rekrutacja.asseco.repository.SaleRateRepository;
import com.rekrutacja.asseco.saleratestrategy.SaleRateDataSourceStrategy;
import com.rekrutacja.asseco.saleratestrategy.SaleRateDataSourceStrategyFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

  private final AverageRateRepository averageRateRepository;
  private final SaleRateRepository saleRateRepository;
  private final AverageRateDataSourceStrategyFactory averageRateDataSourceStrategyFactory;
  private final SaleRateDataSourceStrategyFactory saleRateDataSourceStrategyFactory;

  public List<AverageRateResponse> getAverageRateResponse(final AverageRateRequest rateRequest) {
    final List<AverageRateResponse> response = new ArrayList<>();
    for (final CurrencyRequest currencyRequest : rateRequest.getCurrencies()) {
      final boolean isExistsInDatabase =
          isExistsAverageRateInDatabase(currencyRequest.getCode(), rateRequest.getSearchDate());
      final AverageRateDataSourceStrategy averageRateDataSourceStrategy =
          averageRateDataSourceStrategyFactory.findStrategy(isExistsInDatabase);
      response.add(
          averageRateDataSourceStrategy.getData(
              currencyRequest.getCode(), rateRequest.getSearchDate(), currencyRequest.getAmount()));
    }
    return response;
  }

  public List<SaleRateResponse> getSaleRateResponse(LocalDate searchDate, List<String> codes) {
    final List<SaleRateResponse> response = new ArrayList<>();
    for (final String code : codes) {
      final boolean isExistsInDatabase = isExistsSaleRateInDatabase(code, searchDate);
      final SaleRateDataSourceStrategy saleRateDataSourceStrategy =
          saleRateDataSourceStrategyFactory.findStrategy(isExistsInDatabase);
      response.add(saleRateDataSourceStrategy.getData(code, searchDate));
    }
    return response;
  }

  private boolean isExistsAverageRateInDatabase(final String code, final LocalDate date) {
    return averageRateRepository.isCurrencyExistsByGivenCodeAndDate(code, date) > 0;
  }

  private boolean isExistsSaleRateInDatabase(final String code, final LocalDate date) {
    return saleRateRepository.isCurrencyExistsByGivenCodeAndDate(code, date) > 0;
  }
}
