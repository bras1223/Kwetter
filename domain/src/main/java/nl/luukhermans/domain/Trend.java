package nl.luukhermans.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trend {

    private String hashtag;
    private int timesUsed;

}
