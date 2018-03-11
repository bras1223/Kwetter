package nl.luukhermans.dao.coll;

import nl.luukhermans.dao.TrendDao;
import nl.luukhermans.domain.Trend;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.Collection;
import java.util.HashMap;

@Stateless
@Default
public class TrendsDaoColl implements TrendDao {
    private HashMap<Long, Trend> trends = new HashMap<>();

    public Collection<Trend> getAllTrends() {
        return trends.values();
    }

    @Override
    public Trend findTrendByID(Long ID) {
        return trends.get(ID);
    }

    @Override
    public Trend findTrendByHashtag(String hashtag) {
        for (Trend t : trends.values()) {
            if (t.getHashtag() == hashtag) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void updateTrend(Trend trend) {
        trends.put(trend.getID(), trend);
    }

    @Override
    public void addTrend(Trend trend) {
        trends.put(trend.getID(), trend);
    }

    @Override
    public void deleteTrend(Trend trend) {
        trends.remove(trend.getID());
    }

    @Override
    public int count() {
        return trends.size();
    }
}
