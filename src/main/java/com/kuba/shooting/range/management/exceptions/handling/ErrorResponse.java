package com.kuba.shooting.range.management.exceptions.handling;

import java.time.Instant;

public record ErrorResponse (Instant timestamp,
                             int status,
                             String error,
                             String message,
                             String path){
}
