package com.rekrutacja.asseco.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class SaleRateResponse {

    private String currencyName;
    private String currencyCode;
    private BigDecimal bid;
    private BigDecimal ask;
}
