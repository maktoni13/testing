package ua.kpi.training.controller.command.auth;

import org.junit.Test;
import ua.kpi.training.controller.command.utility.CommandUtility;
import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RegistrationCommandTest {

    @Test
    public void execute() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Locale.setDefault(Locale.ENGLISH);
        RegistrationCommand registrationCommand = new RegistrationCommand();
        String result = registrationCommand.execute(request);
        assertThat(result, is(PageContainer.WEB_INF_REGISTRATION_JSP));
        verify(request, times(2));
    }
}