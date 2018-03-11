package nl.luukhermans.dao.jpa;

import nl.luukhermans.dao.TrendDao;
import nl.luukhermans.domain.Trend;
import nl.luukhermans.util.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TrendDaoJpaIT {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;

    private TrendDao trendDao;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(MessageDaoJpaIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        trendDao = new TrendDaoJPA();
        ((TrendDaoJPA) trendDao).setEm(em);
    }

    @Test
    public void addingTrendSuccessful() {
        Integer expectedResult = 1;
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        tx.begin();
        trendDao.addTrend(trend);
        tx.commit();
        tx.begin();
        int trendCount = trendDao.count();
        tx.commit();
        assertThat(trendCount, is(expectedResult));
    }

    @Test(expected = RollbackException.class)
    public void addingDuplicateHashtagRollback() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();
        Trend trend1 = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        tx.begin();
        trendDao.addTrend(trend);
        trendDao.addTrend(trend1);
        tx.commit();
    }

    @Test
    public void removeTrendSuccessful() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();
        int expectedCount = 0;

        tx.begin();
        trendDao.addTrend(trend);
        tx.commit();
        tx.begin();
        trendDao.deleteTrend(trend);
        tx.commit();
        tx.begin();
        int trendCount = trendDao.count();
        tx.commit();
        assertThat(trendCount, is(expectedCount));
    }

    @Test
    public void findTrendByIdSuccessful() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        tx.begin();
        trendDao.addTrend(trend);
        tx.commit();
        tx.begin();
        Trend foundTrend = trendDao.findTrendByID(trend.getID());
        tx.commit();
        assertThat(foundTrend, is(trend));
    }

    @Test
    public void findUserByHashtagSuccessful() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        tx.begin();
        trendDao.addTrend(trend);
        tx.commit();
        tx.begin();
        Trend foundTrend = trendDao.findTrendByHashtag(trend.getHashtag());
        tx.commit();
        assertThat(foundTrend, is(trend));
    }

    @Test
    public void updateTrendSuccesful() {
        Trend trend = Trend.builder().timesUsed(1).hashtag("Kwetter").build();

        tx.begin();
        trendDao.addTrend(trend);
        tx.commit();
        trend.setTimesUsed(2);
        tx.begin();
        trendDao.updateTrend(trend);
        tx.commit();
        tx.begin();
        Trend foundTrend = trendDao.findTrendByHashtag(trend.getHashtag());
        tx.commit();
        assertThat(foundTrend.getTimesUsed(), is(trend.getTimesUsed()));
    }
}
