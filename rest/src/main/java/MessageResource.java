import dto.MessageDTO;
import nl.luukhermans.domain.Message;
import nl.luukhermans.service.MessageService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("messages")
@Stateless
public class MessageResource {

    @Inject
    MessageService messageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageDTO> getAll() {
        List<MessageDTO> messages = new LinkedList<MessageDTO>();
        for (Message m : messageService.getAllMessages()) {
            messages.add(new MessageDTO(m, true));
        }

        return messages;
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Message createMessage(Message message) {
        try {
            messageService.addMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}