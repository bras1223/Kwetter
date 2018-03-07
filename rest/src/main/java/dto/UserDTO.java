package dto;

import nl.luukhermans.domain.User;

import java.util.LinkedList;
import java.util.List;

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

    }
}
