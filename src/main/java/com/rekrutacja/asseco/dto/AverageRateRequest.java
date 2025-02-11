package com.rekrutacja.asseco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class AverageRateRequest {
    
    private LocalDate searchDate;
    private List<CurrencyRequest> currencies;
}
