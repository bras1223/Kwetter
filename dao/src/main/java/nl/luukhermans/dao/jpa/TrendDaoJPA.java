package nl.luukhermans.dao.jpa;

import nl.luukhermans.dao.JPA;
import nl.luukhermans.dao.TrendDao;
import nl.luukhermans.domain.Trend;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Stateless
@JPA
public class TrendDaoJPA implements TrendDao {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public Collection<Trend> getAllTrends() {
        TypedQuery<Trend> query = em.createNamedQuery("trend.getAllTrends", Trend.class);
        List<Trend> result = query.getResultList();
        return result;
    }

    @Override
    public Trend findTrendByID(Long ID) {
        return em.find(Trend.class, ID);
    }

    @Override
    public Trend findTrendByHashtag(String hashtag) {
        TypedQuery<Trend> query = em.createNamedQuery("trend.findByHashtag", Trend.class);
        query.setParameter("hashtag", hashtag);
        List<Trend> result = query.getResultList();
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public void updateTrend(Trend trend) {
        em.merge(trend);
    }

    @Override
    public void addTrend(Trend trend) {
        em.persist(trend);
    }

    @Override
    public void deleteTrend(Trend trend) {
        em.remove(trend);
    }

    @Override
    public int count() {
        return getAllTrends().size();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
