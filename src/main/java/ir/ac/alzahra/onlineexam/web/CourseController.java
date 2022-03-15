package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.model.data.*;
import ir.ac.alzahra.onlineexam.service.course.CourseService;
import ir.ac.alzahra.onlineexam.service.exam.ExamService;
import ir.ac.alzahra.onlineexam.service.studentexam.StudentExamService;
import ir.ac.alzahra.onlineexam.service.teacher.TeacherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class CourseController {
    private final CourseService courseService;
    private final ExamService examService;
    private final TeacherService teacherService;
    private final StudentExamService studentExamService;

    public CourseController(CourseService courseService, ExamService examService, TeacherService teacherService, StudentExamService studentExamService) {
        this.courseService = courseService;
        this.examService = examService;
        this.teacherService = teacherService;
        this.studentExamService = studentExamService;
    }

    @GetMapping("/courses")
    public Set<Course> getCourses() {
        return courseService.findAll();
    }

    @GetMapping("/courses/add")
    public ModelAndView getAddCoursePage(ModelAndView modelAndView) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        modelAndView.addObject("course", new Course());
        modelAndView.setViewName("course/add-course");
        return modelAndView;
    }

    @PostMapping("/courses/add")
    public ModelAndView addCourse(@Valid @ModelAttribute Course course, BindingResult bindingResult, ModelAndView modelAndView, HttpSession session) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("course/add-course");
            return modelAndView;
        }

        courseService.save(course);
        modelAndView.setViewName("redirect:http://localhost:8080/admin-dashboard/" + session.getAttribute("adminId"));
        return modelAndView;
    }

    @GetMapping("/courses/delete/{id}")
    public ModelAndView deleteCourse(@PathVariable Long id, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        courseService.delete(id);
        modelAndView.setViewName("redirect:http://localhost:8080/admin-dashboard/" + session.getAttribute("adminId"));
        return modelAndView;
    }

    @GetMapping("/courses/{id}/educational-users")
    public Set<EducationalUser> getCourseEducationalUsers(@PathVariable Long id) {
        return courseService.findEducationalUsers(id);
    }

    @GetMapping("/courses/{educationalUserId}")
    public Set<Course> getTeacherCourses(@PathVariable Long educationalUserId) {
        return courseService.findByEducationalUserId(educationalUserId);
    }

    @GetMapping("/courses/{courseId}/exams")
    public Set<Exam> getCourseExams(@PathVariable Long courseId, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        Set<Exam> exams = examService.findByCourseId(courseId);
        exams.forEach(exam -> exam.setMark(studentExamService.getMark(exam.getId(), studentId)));
        return exams;
    }

    @GetMapping("/courses/{courseId}/add-exam")
    public ModelAndView getAddExamToCoursePage(@PathVariable Long courseId, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        session.setAttribute("course", courseService.findById(courseId));
        modelAndView.setViewName("exam/add-exam");
        modelAndView.addObject("exam", new Exam());
        return modelAndView;
    }

    @PostMapping("/courses/add-exam")
    public ModelAndView addExamToCoursePage(@ModelAttribute Exam exam, ModelAndView modelAndView, HttpSession session) {
        Course course = (Course) session.getAttribute("course");
        exam.setCourse(course);

        Long teacherId = (Long) session.getAttribute("teacherId");
        Teacher teacher = teacherService.findById(teacherId);
        exam.setTeacher(teacher);

        exam.setStopped(Boolean.FALSE);
        examService.save(exam);

        modelAndView.setViewName("redirect:http://localhost:8080/teacher-dashboard/" + session.getAttribute("teacherId"));
        return modelAndView;
    }
}
