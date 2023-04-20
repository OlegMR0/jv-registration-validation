package core.basesyntax.service;

import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {
    private static final RegistrationService REGISTRATION_SERVICE = new RegistrationServiceImpl();
    private User defaultCorrectUser;

    @BeforeEach
    void setUp() {
        defaultCorrectUser = new User();
        defaultCorrectUser.setAge(20);
        defaultCorrectUser.setLogin("correct login");
        defaultCorrectUser.setPassword("correctPassword");
    }

    @Test
    void register_NullUser_NotOk() {
        User user = null;
        assertThrows(InvalidUserException.class, () -> REGISTRATION_SERVICE.register(user));
    }

    @Test
    void register_MinorUser_NotOk() {
        defaultCorrectUser.setAge(15);
        assertThrows(InvalidUserException.class, () -> REGISTRATION_SERVICE.register(defaultCorrectUser));
    }

    @Test
    void register_IncorrectPassword_NotOk() {
        defaultCorrectUser.setPassword("123");
        assertThrows(InvalidUserException.class, () -> REGISTRATION_SERVICE.register(defaultCorrectUser));
    }

    @Test
    void register_IncorrectLogin_NotOk() {
        defaultCorrectUser.setLogin("4321");
        assertThrows(InvalidUserException.class, () -> REGISTRATION_SERVICE.register(defaultCorrectUser));
    }
}