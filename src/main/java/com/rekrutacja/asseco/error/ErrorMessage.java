package com.rekrutacja.asseco.error;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String source;
    private LocalDateTime dateTime;
    private String reasonCode;
    private String message;
}

