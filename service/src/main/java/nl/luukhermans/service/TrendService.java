package nl.luukhermans.service;

import nl.luukhermans.dao.JPA;
import nl.luukhermans.dao.TrendDao;
import nl.luukhermans.domain.Trend;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class TrendService {

    @Inject
    @JPA
    private TrendDao trendDao;

    /**
     * Gets all of the trends from the TrendDao.
     *
     * @return all known trends at the TrendDao
     */
    public Collection<Trend> getAllTrends() {
        return trendDao.getAllTrends();
    }


    /**
     * Adds a trend if it doesn't excist on the TrendDao,
     * ups it if it excists.
     *
     * @param hashtag The hashtag that should be added or upped.
     */
    public Trend addOrUpTrend(String hashtag) {
        Objects.requireNonNull(hashtag);
        Trend trend = trendDao.findTrendByHashtag(hashtag);

        if (trend != null) {
            trend.setTimesUsed(trend.getTimesUsed() + 1);
            trendDao.updateTrend(trend);
            return trend;
        } else {
            Trend newTrend = Trend.builder().hashtag(hashtag).timesUsed(1).build();
            trendDao.addTrend(newTrend);
            return newTrend;
        }
    }

    /**
     * Deletes a trend if it is only 1 time used,
     * downs if more.
     *
     * @param hashtag The hashtag that should be deleted or downed.
     */
    public Trend downOrDeleteTrend(String hashtag) {
        Objects.requireNonNull(hashtag);
        Trend trend = trendDao.findTrendByHashtag(hashtag);

        if (trend.getTimesUsed() == 1) {
            trend.setTimesUsed(trend.getTimesUsed() - 1);
            trendDao.deleteTrend(trend);
            return trend;
        } else {
            trend.setTimesUsed(trend.getTimesUsed() - 1);
            trendDao.updateTrend(trend);
            return trend;
        }
    }

    public List<Trend> addTrendsFromMessage(String message, boolean add) {
        Pattern HashPattern = Pattern.compile("#(\\S+)");
        Matcher matcher = HashPattern.matcher(message);

        List<Trend> trends = new ArrayList<Trend>();
        while (matcher.find()) {
            if (add) {
                trends.add(addOrUpTrend(matcher.group(1)));
            } else {
                downOrDeleteTrend(matcher.group(1));
            }
        }

        return trends;
    }

}
