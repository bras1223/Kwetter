package dto;

import lombok.Data;
import nl.luukhermans.domain.Message;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageDTO implements Serializable {

    private Long ID;

    private UserDTO sender;

    private String message;

    private LocalDateTime timestamp;

    public MessageDTO(Message message) {
        this.ID = message.getID();
        this.message = message.getMessage();
        this.timestamp = message.getTimestamp();
    }

    public MessageDTO(Message message, boolean filled) {
        this(message);

        if (filled) {
            this.sender = new UserDTO(message.getSender());
        }
    }

}
