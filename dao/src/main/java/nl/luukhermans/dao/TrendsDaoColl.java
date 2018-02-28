package nl.luukhermans.dao;

import nl.luukhermans.domain.Trend;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.HashSet;
import java.util.Set;

@Stateless
@Default
public class TrendsDaoColl implements TrendDao {
    private Set<Trend> trends = new HashSet<Trend>();

    public Set<Trend> getAllTrends() {
        return trends;
    }

    public void addOrUpTrend() {

    }

    public void removeOrDownTrend() {

    }


}
