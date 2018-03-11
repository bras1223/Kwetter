package dto;

import lombok.Data;
import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;

import java.util.LinkedList;
import java.util.List;

@Data
public class UserDTO {

    private Long ID;

    private List<MessageDTO> messages = new LinkedList<>();

    private List<UserDTO> followers = new LinkedList<>();

    private List<UserDTO> following = new LinkedList<>();


    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String username;
    private String bio;
    private String webpage;

    private String location;

    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.avatarUrl = user.getAvatarUrl();
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.webpage = user.getWebpage();
    }

    public UserDTO(User user, boolean filled) {
        this(user);

        if (filled) {
            for (User u : user.getFollowers()) {
                followers.add(new UserDTO(u));
            }

            for (User u : user.getFollowing()) {
                following.add(new UserDTO(u));
            }

            for (Message m : user.getMessages()) {
                messages.add(new MessageDTO(m));
            }
        }
    }
}
