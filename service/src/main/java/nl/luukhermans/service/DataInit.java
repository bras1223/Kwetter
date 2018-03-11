package nl.luukhermans.service;

import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;
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

        try {
            messageService.addMessage(Message.builder().sender(user3).message("Hey hallo!").timestamp(LocalDateTime.now()).build());
            messageService.addMessage(Message.builder().sender(user3).message("Mijn eerste kweet.").timestamp(LocalDateTime.now()).build());
            messageService.addMessage(Message.builder().sender(user3).message("#kwetter is gaaf!").timestamp(LocalDateTime.now()).build());
            messageService.addMessage(Message.builder().sender(user2).message("Berichtje.").timestamp(LocalDateTime.now()).build());
            messageService.addMessage(Message.builder().sender(user4).message("Hey hallo!").timestamp(LocalDateTime.now()).build());
            messageService.addMessage(Message.builder().sender(user5).message("Mooi he?!").timestamp(LocalDateTime.now()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
