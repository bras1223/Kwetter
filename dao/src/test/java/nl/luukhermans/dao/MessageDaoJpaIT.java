package nl.luukhermans.dao;

import nl.luukhermans.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MessageDaoJpaIT {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private User testUser;
    private MessageDao messageDao;
/**
    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(MessageDaoJpaIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        testUser = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();
        messageDao = new MessageDaoJPA();
        ((MessageDaoJPA) messageDao).setEm(em);
    }

    @Test
    public void savingMessageSuccesful() {
        Integer expectedResult = 1;
        Message message = Message.builder().message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();

        tx.begin();
        messageDao.addMessage(message);
        tx.commit();
        tx.begin();
        int messageCount = messageDao.count();
        tx.commit();
        assertThat(messageCount, is(expectedResult));
    }**/

}
