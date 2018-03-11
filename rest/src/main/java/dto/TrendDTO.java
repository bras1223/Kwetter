package dto;

import lombok.Data;
import nl.luukhermans.domain.Trend;


@Data
public class TrendDTO {

    private Long ID;

    private String hashtag;

    private int timesUsed;

    public TrendDTO(Trend trend) {
        this.ID = trend.getID();
        this.hashtag = trend.getHashtag();
        this.timesUsed = trend.getTimesUsed();
    }

}
