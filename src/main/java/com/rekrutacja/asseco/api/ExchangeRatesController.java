package com.rekrutacja.asseco.api;

import com.rekrutacja.asseco.dto.AverageRateRequest;
import com.rekrutacja.asseco.dto.AverageRateResponse;
import java.time.LocalDate;
import java.util.List;

import com.rekrutacja.asseco.dto.SaleRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rates")
public class ExchangeRatesController {

  private final ExchangeRatesService exchangeRatesService;

  @PostMapping("/average")
  public ResponseEntity<List<AverageRateResponse>> getAverageRateResponse(
      @RequestBody AverageRateRequest rateRequest) {
    final List<AverageRateResponse> response =
        exchangeRatesService.getAverageRateResponse(rateRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/sale")
  public ResponseEntity<List<SaleRateResponse>> getSaleRateResponse(
      @RequestParam LocalDate searchDate, @RequestParam List<String> codes) {
    final List<SaleRateResponse> response =
        exchangeRatesService.getSaleRateResponse(searchDate, codes);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
