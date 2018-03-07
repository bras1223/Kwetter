import nl.luukhermans.domain.User;
import nl.luukhermans.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
@Stateless
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return (List) userService.getAllUsers();
    }

    @GET
    @Path("/un={username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByID(@PathParam("username") String username) throws Exception {
        User user = userService.findUserByUsername(username);
        return user;
    }

    @GET
    @Path("/id={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUsername(@PathParam("id") Long ID) throws Exception {
        User user = userService.findUserByID(ID);
        return user;
    }
}
