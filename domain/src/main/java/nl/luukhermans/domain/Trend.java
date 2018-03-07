package nl.luukhermans.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "trend.findById", query = "SELECT t FROM Trend t WHERE t.ID = :ID"),
        @NamedQuery(name = "trend.findByHashtag", query = "SELECT t FROM Trend t WHERE t.hashtag = :hashtag"),
        @NamedQuery(name = "trend.getAllTrends", query = "SELECT t FROM Trend t"),
})
public class Trend implements Serializable {

    @Id
    @GeneratedValue
    private Long ID;

    @Column(unique = true)
    @NonNull
    private String hashtag;

    private int timesUsed;

}
