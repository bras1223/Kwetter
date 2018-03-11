import dto.UserDTO;
import nl.luukhermans.domain.User;
import nl.luukhermans.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("users")
@Stateless
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getAll() {
        List<UserDTO> users = new LinkedList<UserDTO>();
        for (User u : userService.getAllUsers()) {
            users.add(new UserDTO(u, true));
        }
        return users;
    }

    @GET
    @Path("/un={username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserByID(@PathParam("username") String username) throws Exception {
        UserDTO user = new UserDTO(userService.findUserByUsername(username), true);
        return user;
    }

    @GET
    @Path("/id={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserByUsername(@PathParam("id") Long ID) throws Exception {
        UserDTO user = new UserDTO(userService.findUserByID(ID), true);
        return user;
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User createUser(User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @POST
    @Path("{id}/following/{following}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserDTO followUser(@PathParam("id") Long ID, @PathParam("following") Long Following) {
        User user = null;
        try {
            user = userService.findUserByID(ID);
            userService.followUser(user, userService.findUserByID(Following));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserDTO(user, true);
    }

    @DELETE
    @Path("{id}/following/{following}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserDTO unfollowUser(@PathParam("id") Long ID, @PathParam("following") Long Following) {
        User user = null;
        try {
            user = userService.findUserByID(ID);
            userService.unfollowUser(user, userService.findUserByID(Following));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserDTO(user, true);
    }
}
