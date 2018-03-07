package nl.luukhermans.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "message.findAllMessages", query = "SELECT m FROM Message m"),
        @NamedQuery(name = "message.findById", query = "SELECT m FROM Message m WHERE m.ID = :ID")})
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private User sender;

    @NonNull
    private String message;

    @NonNull
    private LocalDateTime timestamp;

}
