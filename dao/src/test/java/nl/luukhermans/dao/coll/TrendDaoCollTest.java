package nl.luukhermans.dao.coll;

import nl.luukhermans.dao.TrendDao;
import nl.luukhermans.domain.Trend;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TrendDaoCollTest {

    private TrendDao trendDao;

    @Before
    public void setUp() {
        trendDao = new TrendsDaoColl();
    }

    @Test
    public void addingTrendSuccessful() {
        Integer expectedResult = 1;
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        trendDao.addTrend(trend);
        int trendCount = trendDao.count();
        assertThat(trendCount, is(expectedResult));
    }

    @Test
    public void removeTrendSuccessful() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();
        int expectedCount = 0;

        trendDao.addTrend(trend);
        trendDao.deleteTrend(trend);
        int trendCount = trendDao.count();
        assertThat(trendCount, is(expectedCount));
    }

    @Test
    public void findTrendByIdSuccessful() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        trendDao.addTrend(trend);
        Trend foundTrend = trendDao.findTrendByID(trend.getID());
        assertThat(foundTrend, is(trend));
    }

    @Test
    public void findUserByHashtagSuccessful() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        trendDao.addTrend(trend);
        Trend foundTrend = trendDao.findTrendByHashtag(trend.getHashtag());
        assertThat(foundTrend, is(trend));
    }

    @Test
    public void updateTrendSuccesful() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        trendDao.addTrend(trend);
        trend.setTimesUsed(2);
        trendDao.updateTrend(trend);
        Trend foundTrend = trendDao.findTrendByHashtag(trend.getHashtag());
        assertThat(foundTrend.getTimesUsed(), is(trend.getTimesUsed()));
    }
}
