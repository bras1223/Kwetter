package nl.luukhermans.dao;

import nl.luukhermans.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Stateless
@JPA
public class UserDaoJPA implements UserDao {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public Collection<User> getAllUsers() {
        return null;
    }

    @Override
    public User findByID(int ID) {
        TypedQuery<User> query = em.createNamedQuery("user.findById", User.class);
        query.setParameter("ID", ID);
        List<User> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @PostConstruct
    public void init() {
        //System.out.println("---UserDaoJPA");
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
