package nl.luukhermans.service;

import nl.luukhermans.dao.JPA;
import nl.luukhermans.dao.TrendDao;
import nl.luukhermans.domain.Trend;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Objects;

@Stateless
public class TrendService {

    @Inject
    @JPA
    private TrendDao trendDao;


    public void addOrUpTrend(String hashtag) {
        Objects.requireNonNull(hashtag);
        Trend trend = trendDao.findTrendByHashtag(hashtag);

        if (trend != null) {
            trend.setTimesUsed(trend.getTimesUsed() + 1);
            trendDao.updateTrend(trend);
        } else {
            Trend newTrend = Trend.builder().hashtag(hashtag).timesUsed(1).build();
            trendDao.addTrend(newTrend);
        }
    }

    public void downOrDeleteTrend(String hashtag) {
        Objects.requireNonNull(hashtag);
        Trend trend = trendDao.findTrendByHashtag(hashtag);

        if (trend.getTimesUsed() == 1) {
            trendDao.deleteTrend(trend);
        } else {
            trend.setTimesUsed(trend.getTimesUsed() - 1);
            trendDao.updateTrend(trend);
        }
    }
}
