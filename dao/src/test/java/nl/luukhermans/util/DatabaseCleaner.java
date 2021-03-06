package nl.luukhermans.util;

import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.Trend;
import nl.luukhermans.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.sql.SQLException;

public class DatabaseCleaner {
    private static final Class<?>[] ENTITY_TYPES = {
            Message.class,
            User.class,
            Trend.class
    };
    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() throws SQLException {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.getTransaction().commit();
        em.close();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("delete from " + getEntityName(entityType)).executeUpdate();
    }

    protected String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}
