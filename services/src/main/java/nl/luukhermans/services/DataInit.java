package nl.luukhermans.services;

import nl.luukhermans.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class DataInit {

    @Inject
    private UserService userService;

    @PostConstruct
    public void initialize() {
        userService.addUser(User.builder().firstName("Luuk").lastName("Hermans").build());
    }


}
