package com.rekrutacja.asseco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyRequest {

    private BigDecimal amount;
    private String code;
}
