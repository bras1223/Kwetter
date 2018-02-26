import domain.Trend;

import java.util.Collection;

public interface TrendDao {

    Collection<Trend> getAllTrends();

    void addOrUpTrend();

    void removeOrDownTrend();

}
