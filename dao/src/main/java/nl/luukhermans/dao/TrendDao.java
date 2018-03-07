package nl.luukhermans.dao;

import nl.luukhermans.domain.Trend;

import java.util.Collection;

public interface TrendDao {

    Collection<Trend> getAllTrends();

    Trend findTrendByID(Long ID);

    Trend findTrendByHashtag(String hashtag);

    void updateTrend(Trend trend);

    void addTrend(Trend trend);

    void deleteTrend(Trend trend);

    int count();

}
