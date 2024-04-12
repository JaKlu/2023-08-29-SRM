package ovh.jakubk.shooting.range.management.exceptions.handling;

import java.time.Instant;

public record ErrorResponse (Instant timestamp,
                             int status,
                             String message,
                             String description,
                             String path){
}
