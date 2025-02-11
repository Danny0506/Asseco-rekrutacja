package com.rekrutacja.asseco.saleratestrategy;

import static com.rekrutacja.asseco.util.ClientUtil.buildWebClient;
import static com.rekrutacja.asseco.util.ClientUtil.createAdditionalUri;
import static java.util.Objects.nonNull;

import com.rekrutacja.asseco.dto.ExchangeRateResponse;
import com.rekrutacja.asseco.dto.SaleRateResponse;
import com.rekrutacja.asseco.entity.SaleRateEntity;
import com.rekrutacja.asseco.repository.SaleRateRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SaleRateDataFromApiStrategy implements SaleRateDataSourceStrategy {

  private final SaleRateRepository saleRateRepository;
  private String url;

  public SaleRateDataFromApiStrategy(
      @Value("${api.table.sale.url}") String url, final SaleRateRepository saleRateRepository) {
    this.url = url;
    this.saleRateRepository = saleRateRepository;
  }

  @Override
  public boolean shouldApply(boolean isDataFromDatabase) {
    return !isDataFromDatabase;
  }

  @Override
  public SaleRateResponse getData(final String code, final LocalDate date) {
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
      return createRateResponse(result);
    } else {
      throw new RuntimeException("API not return correct format of data");
    }
  }

  private void createNewEntity(ExchangeRateResponse result, LocalDate date) {
    final SaleRateEntity saleRateEntity = new SaleRateEntity();
    saleRateEntity.setCode(result.getCode());
    saleRateEntity.setDate(date);
    saleRateEntity.setBid(result.getRates().get(0).getBid());
    saleRateEntity.setAsk(result.getRates().get(0).getAsk());
    saleRateEntity.setCurrencyName(result.getCurrency());

    saleRateRepository.save(saleRateEntity);
  }

  private SaleRateResponse createRateResponse(final ExchangeRateResponse exchangeRateResponse) {
    final SaleRateResponse saleRateResponse = new SaleRateResponse();
    saleRateResponse.setAsk(exchangeRateResponse.getRates().get(0).getAsk());
    saleRateResponse.setBid(exchangeRateResponse.getRates().get(0).getBid());
    saleRateResponse.setCurrencyName(exchangeRateResponse.getCurrency());
    saleRateResponse.setCurrencyCode(exchangeRateResponse.getCode());

    return saleRateResponse;
  }
}
