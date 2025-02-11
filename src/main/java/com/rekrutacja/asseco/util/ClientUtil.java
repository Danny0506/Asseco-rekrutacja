package com.rekrutacja.asseco.util;

import java.net.URI;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.reactive.function.client.WebClient;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientUtil {

  public static WebClient buildWebClient() {
    return WebClient.builder().build();
  }

  @SneakyThrows
  public static URI createAdditionalUri(final String url, final String code, final LocalDate date) {
    return new URI(url + code.toLowerCase() + "/" + date);
  }
}
