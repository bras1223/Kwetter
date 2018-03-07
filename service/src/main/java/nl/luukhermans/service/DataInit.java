package nl.luukhermans.service;

import nl.luukhermans.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Logger;

@Startup
@Singleton
public class DataInit {

    @Inject
    private UserService userService;

    @Inject
    MessageService messageService;

    Logger logger;

    @PostConstruct
    public void initialize() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();
        User user2 = User.builder().firstName("Raymond").lastName("Limpens").username("raymond.limpens").build();
        User user3 = User.builder().firstName("Robin").lastName("Laugs").username("robin.laugs").build();
        User user4 = User.builder().firstName("Lesley").lastName("Vente").username("lesley.vente").build();
        User user5 = User.builder().firstName("Oscar").lastName("de Leeuw").username("oscar.deleeuw").build();

        userService.addUser(user);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        userService.addUser(user5);

        try {
            user = userService.findUserByUsername("luuk.hermans");
            user2 = userService.findUserByUsername("raymond.limpens");
            user3 = userService.findUserByUsername("robin.laugs");
            user4 = userService.findUserByUsername("lesley.vente");
            user5 = userService.findUserByUsername("oscar.deleeuw");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print(user);
        System.out.print(user2);
        try {
            messageService.addMessage(user.getID(), "Hey hallo!");
            messageService.addMessage(user.getID(), "Mijn eerste kweet.");
            messageService.addMessage(user3.getID(), "#kwetter is gaaf!");
            messageService.addMessage(user2.getID(), "Testberichtje");
            messageService.addMessage(user4.getID(), "Hey hallo!");
            messageService.addMessage(user5.getID(), "Mooi he?!");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
