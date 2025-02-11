package com.rekrutacja.asseco.averageratestrategy;

import static com.rekrutacja.asseco.util.ClientUtil.buildWebClient;
import static com.rekrutacja.asseco.util.ClientUtil.createAdditionalUri;
import static java.util.Objects.nonNull;

import com.rekrutacja.asseco.dto.AverageRateResponse;
import com.rekrutacja.asseco.dto.ExchangeRateResponse;
import com.rekrutacja.asseco.entity.AverageRateEntity;
import com.rekrutacja.asseco.repository.AverageRateRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class AverageRateDataFromApiStrategy implements AverageRateDataSourceStrategy {

  private final AverageRateRepository averageRateRepository;
  private String url;

  public AverageRateDataFromApiStrategy(
      @Value("${api.table.average.url}") String url,
      final AverageRateRepository averageRateRepository) {
    this.url = url;
    this.averageRateRepository = averageRateRepository;
  }

  @Override
  public boolean shouldApply(boolean isDataFromDatabase) {
    return !isDataFromDatabase;
  }

  @Override
  public AverageRateResponse getData(final String code, final LocalDate date, BigDecimal amount) {
    final ExchangeRateResponse result =
        buildWebClient()
            .get()
            .uri(createAdditionalUri(url, code, date))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(ExchangeRateResponse.class)
            .block();

    if (nonNull(result)) {
      createNewEntity(result, date);
      return new AverageRateResponse(
          result.getRates().get(0).getMid().multiply(amount), result.getCurrency());
    } else {
      throw new RuntimeException("API not return correct format of data");
    }
  }

  private void createNewEntity(ExchangeRateResponse result, LocalDate date) {
    final AverageRateEntity averageRateEntity = new AverageRateEntity();
    averageRateEntity.setCode(result.getCode());
    averageRateEntity.setDate(date);
    averageRateEntity.setMid(result.getRates().get(0).getMid());
    averageRateEntity.setCurrencyName(result.getCurrency());

    averageRateRepository.save(averageRateEntity);
  }
}
