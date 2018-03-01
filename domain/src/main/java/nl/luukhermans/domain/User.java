package nl.luukhermans.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

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
    @GeneratedValue
    private int ID;

    @Builder.Default
    private Set<Message> messages = new TreeSet<>();
    @Builder.Default
    private Set<User> followers = new TreeSet<>();
    @Builder.Default
    private Set<User> following = new TreeSet<>();

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

    public void followedBy(User user) {
        followers.add(user);
    }

    public void addMessage(Message message) {
        messages.add(message);
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
