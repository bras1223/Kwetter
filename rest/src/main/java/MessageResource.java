import nl.luukhermans.domain.Message;
import nl.luukhermans.service.MessageService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
@Stateless
public class MessageResource {

    @Inject
    MessageService messageService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAll() {
        return (List) messageService.getAllMessages();
    }

}