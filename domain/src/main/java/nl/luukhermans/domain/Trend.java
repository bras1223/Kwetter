package nl.luukhermans.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
public class Trend implements Serializable {

    @Id
    @GeneratedValue
    private int ID;

    private String hashtag;
    private int timesUsed;

}
