package nl.luukhermans.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "message.findAllMessages", query = "SELECT m FROM Message m"),
        @NamedQuery(name = "message.findById", query = "SELECT m FROM Message m WHERE m.ID = :ID")})
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private User sender;

    @NonNull
    private String message;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "mentions")
    private List<User> mentions = new LinkedList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "trends")
    private List<Trend> trends = new LinkedList<>();

    @NonNull
    private LocalDateTime timestamp;

    public void addMention(User mentioned) {
        this.mentions.add(mentioned);
    }

    public void addTrend(Trend trend) {
        this.trends.add(trend);
    }

}
