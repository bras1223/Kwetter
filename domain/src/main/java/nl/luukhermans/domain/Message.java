package nl.luukhermans.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class Message {

    private final int ID;

    @NonNull
    private User sender;

    @NonNull
    private String message;

    private LocalDateTime timestamp;

}
