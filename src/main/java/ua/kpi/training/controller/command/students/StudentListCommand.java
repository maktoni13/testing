package ua.kpi.training.controller.command.students;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;

public class StudentListCommand implements Command {
    private StudentService studentService;

    public StudentListCommand() {
    }

    public StudentListCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PageContainer.STUDENT_LIST_ATTR,
                studentService.getAllStudents());
        return PageContainer.WEB_INF_ADMIN_STUDENTS_LIST_JSP;
    }
}
