package com.rekrutacja.asseco.averageratestrategy;

import com.rekrutacja.asseco.dto.AverageRateResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AverageRateDataSourceStrategy {

    boolean shouldApply(final boolean isDataFromDatabase);
    AverageRateResponse getData(final String code, final LocalDate date, BigDecimal amount);
}
