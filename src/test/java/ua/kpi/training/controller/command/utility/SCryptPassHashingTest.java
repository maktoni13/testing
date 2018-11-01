package ua.kpi.training.controller.command.utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SCryptPassHashingTest {

    @Test
    public void cryptPassPositiveScenario() {
        assertTrue(
                SCryptPassHashing.validPassword("password1",
                        SCryptPassHashing.cryptPass("password1")));
    }

    @Test
    public void cryptPassNegativeScenario() {
        assertFalse(
                SCryptPassHashing.validPassword("password1",
                        SCryptPassHashing.cryptPass("password2")));
    }


    @Test
    public void validPasswordPositiveScenario() {
        assertTrue(SCryptPassHashing.validPassword(
                "user",
                "$s0$e0801$6vKHD+4ekaIUW00YKuLVLQ==$TaHbMa63jfK4s4Lfhnnw7Bu0X7dHiRJAgBtsT9H7O+g="));
    }

    @Test
    public void validPasswordNegativeScenario() {
        assertFalse(SCryptPassHashing.validPassword(
                "admin",
                "$s0$e0801$6vKHD+4ekaIUW00YKuLVLQ==$TaHbMa63jfK4s4Lfhnnw7Bu0X7dHiRJAgBtsT9H7O+g="));
    }

}