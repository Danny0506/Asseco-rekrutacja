package com.rekrutacja.asseco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    private String no;
    private String effectiveDate;
    private BigDecimal mid;
    private BigDecimal bid;
    private BigDecimal ask;
}
