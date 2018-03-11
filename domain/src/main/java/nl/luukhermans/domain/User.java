package nl.luukhermans.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "user.findById", query = "SELECT u FROM User u WHERE u.ID = :ID"),
        @NamedQuery(name = "user.getAllUsers", query = "SELECT u FROM User u"),
        @NamedQuery(name = "user.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Message> messages = new LinkedList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "followers")
    private List<User> followers = new LinkedList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "following")
    private List<User> following = new LinkedList<>();

    @Builder.Default
    @JoinTable(name = "mentions")
    private List<Message> mentions = new LinkedList<>();

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String avatarUrl;

    @Column(unique = true)
    @NonNull
    private String username;
    private String bio;
    private String webpage;

    private String location;

    public void followUser(User user) {
        following.add(user);
    }

    public void unfollowUser(User user) {
        following.remove(user);
    }

    public void followedBy(User user) {
        followers.add(user);
    }

    public void unfollowedBy(User user) {
        followers.remove(user);
    }


    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addMention(Message message) {
        mentions.add(message);
    }

    public void deleteMention(Message message) {
        mentions.remove(message);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.username, other.username);
    }
}
