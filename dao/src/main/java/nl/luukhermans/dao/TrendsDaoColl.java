import domain.Trend;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class TrendsDaoColl implements TrendDao {
    private Set<Trend> trends = new HashSet<Trend>();

    public Collection<Trend> getAllTrends() {
        return trends;
    }

    public void addOrUpTrend() {

    }

    public void removeOrDownTrend() {

    }


}
