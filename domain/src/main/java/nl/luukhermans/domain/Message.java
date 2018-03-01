package nl.luukhermans.domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "message.findAllMessages", query = "SELECT m FROM Message m")})
public class Message implements Serializable {

    @Id
    @GeneratedValue
    private int ID;

    @NonNull
    private User sender;

    @NonNull
    private String message;

    @NonNull
    private LocalDateTime timestamp;

}
