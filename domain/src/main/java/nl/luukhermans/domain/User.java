package nl.luukhermans.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;
import java.util.TreeSet;

@Data
@Builder
public class User {

    private final int ID;

    private Set<User> followers = new TreeSet<>();
    private Set<User> following = new TreeSet<>();

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String avatarUrl;
    private String username;
    private String bio;
    private String webpage;

    private String location;

    public void followUser(User user) {
        following.add(user);
    }

    public void followedBy(User user) {
        followers.add(user);
    }
}
