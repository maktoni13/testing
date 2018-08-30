package ua.kpi.training.controller.command.students;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.CommonService;
import ua.kpi.training.model.service.StudentService;
import ua.kpi.training.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class StudentListCommand implements Command {

    @Override
    public CommonService getService() {
        return ServiceFactory.getInstance().getStudentService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        StudentService studentService = (StudentService) getService();
        request.setAttribute(PageContainer.STUDENT_LIST_ATTR,
                studentService.getAllStudents());
        return PageContainer.PAGE_STUDENT_LIST;
    }
}
