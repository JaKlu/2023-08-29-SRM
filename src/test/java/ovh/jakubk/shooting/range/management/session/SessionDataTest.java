package ovh.jakubk.shooting.range.management.session;

import ovh.jakubk.shooting.range.management.model.Address;
import ovh.jakubk.shooting.range.management.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SessionDataTest {
    @Test
    public void isUserNotLoggedTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(null);

        boolean actual = sessionData.isLogged();

        Assertions.assertFalse(actual);
    }

    @Test
    public void isUserNullByDefaultTest() {
        SessionData sessionData = new SessionData();
        User actual = sessionData.getUser();

        Assertions.assertNull(actual);
    }

    @Test
    public void isUserLoggedTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User());

        boolean actual = sessionData.isLogged();

        Assertions.assertTrue(actual);
    }

    // notLogged
    @Test
    public void isNotLoggedUserAdminTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(null);

        boolean actual = sessionData.isAdmin();

        Assertions.assertFalse(actual);
    }

    @Test
    public void isNotLoggedUserEmployeeTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(null);

        boolean actual = sessionData.isEmployee();

        Assertions.assertFalse(actual);
    }

    @Test
    public void isNotLoggedUserAdminOrEmployeeTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(null);

        boolean actual = sessionData.isAdminOrEmployee();

        Assertions.assertFalse(actual);
    }

    // isAdmin
    @Test
    public void isLoggedAdminTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.ADMIN));

        boolean actual = sessionData.isAdmin();

        Assertions.assertTrue(actual);
    }

    @Test
    public void isLoggedUserNotAdminTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.USER));

        boolean actual = sessionData.isAdmin();

        Assertions.assertFalse(actual);
    }

    @Test
    public void isLoggedEmployeeNotAdminTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.EMPLOYEE));

        boolean actual = sessionData.isAdmin();

        Assertions.assertFalse(actual);
    }

    // isEmployee
    @Test
    public void isLoggedEmployeeTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.EMPLOYEE));

        boolean actual = sessionData.isEmployee();

        Assertions.assertTrue(actual);
    }

    @Test
    public void isLoggedUserNotEmployeeTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.USER));

        boolean actual = sessionData.isEmployee();

        Assertions.assertFalse(actual);
    }

    @Test
    public void isLoggedAdminNotEmployeeTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.ADMIN));

        boolean actual = sessionData.isEmployee();

        Assertions.assertFalse(actual);
    }

    // isAdminOrEmployee
    @Test
    public void isLoggedAdminAdminOrEmployeeTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.ADMIN));

        boolean actual = sessionData.isAdminOrEmployee();

        Assertions.assertTrue(actual);
    }

    @Test
    public void isLoggedEmployeeAdminOrEmployeeTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.EMPLOYEE));

        boolean actual = sessionData.isAdminOrEmployee();

        Assertions.assertTrue(actual);
    }

    @Test
    public void isLoggedUserNotAdminOrEmployeeTest() {
        SessionData sessionData = new SessionData();
        sessionData.setUser(new User(534L, "asdas", "asdasd", "Asdsad", "asdasda",
                LocalDate.now(), "qweqwewqe", "654654", new Address(), "asdasd",
                new ArrayList<>(), User.Role.USER));

        boolean actual = sessionData.isAdminOrEmployee();

        Assertions.assertFalse(actual);
    }

    //getInfo
    @Test
    public void getFormInfoIfNotNull() {
        SessionData sessionData = new SessionData();
        sessionData.setFormInfo("info");
        String expected = "info";
        String expected2 = "";

        String actual = sessionData.getFormInfo();
        String actual2 = sessionData.getFormInfo();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    public void getFormInfoIfNull() {
        SessionData sessionData = new SessionData();
        sessionData.setFormInfo(null);
        String expected = "";
        String expected2 = "";

        String actual = sessionData.getFormInfo();
        String actual2 = sessionData.getFormInfo();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    public void getFormErrorIfNotNull() {
        SessionData sessionData = new SessionData();
        sessionData.setFormInfo("error");
        String expected = "error";
        String expected2 = "";

        String actual = sessionData.getFormInfo();
        String actual2 = sessionData.getFormInfo();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    public void getFormErrorIfNull() {
        SessionData sessionData = new SessionData();
        sessionData.setFormInfo(null);
        String expected = "";
        String expected2 = "";

        String actual = sessionData.getFormInfo();
        String actual2 = sessionData.getFormInfo();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }
}