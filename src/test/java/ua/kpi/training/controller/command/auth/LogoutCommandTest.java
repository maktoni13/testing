package ua.kpi.training.controller.command.auth;

import org.junit.Test;
import ua.kpi.training.controller.command.utility.CommandUtility;
import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LogoutCommandTest {

    @Test
    public void execute() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        LogoutCommand logoutCommand = new LogoutCommand();
        String result = logoutCommand.execute(request);
        assertThat(result, is(PageContainer.PATH_PREFIX_REDIRECT + PageContainer.PATH_COMMAND_INDEX));
        verify(request, times(1));
        CommandUtility.invalidateCurrentSession(request);
    }
}