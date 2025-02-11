package com.rekrutacja.asseco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponse {
  private String table;
  private String currency;
  private String code;
  private List<Rate> rates;
}
