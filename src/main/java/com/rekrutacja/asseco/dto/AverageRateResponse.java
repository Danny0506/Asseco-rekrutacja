package com.rekrutacja.asseco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AverageRateResponse {
    private BigDecimal price;
    private String currencyName;
}
