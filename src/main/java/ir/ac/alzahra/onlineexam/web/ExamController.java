package ir.ac.alzahra.onlineexam.web;

import ir.ac.alzahra.onlineexam.dto.QuestionDto;
import ir.ac.alzahra.onlineexam.dto.StudentAnswerDto;
import ir.ac.alzahra.onlineexam.model.data.*;
import ir.ac.alzahra.onlineexam.service.answer.AnswerService;
import ir.ac.alzahra.onlineexam.service.exam.ExamService;
import ir.ac.alzahra.onlineexam.service.student.StudentService;
import ir.ac.alzahra.onlineexam.service.studentexam.StudentExamService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@RestController
public class ExamController {
    private final ExamService examService;
    private final AnswerService answerService;
    private final StudentService studentService;
    private final StudentExamService studentExamService;

    public ExamController(ExamService examService, AnswerService answerService, StudentService studentService, StudentExamService studentExamService) {
        this.examService = examService;
        this.answerService = answerService;
        this.studentService = studentService;
        this.studentExamService = studentExamService;
    }

    @GetMapping("/exams/stop/{id}")
    public ModelAndView stopExam(@PathVariable Long id, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        examService.stopExam(id);
        modelAndView.setViewName("redirect:http://localhost:8080/teacher-dashboard/" + session.getAttribute("teacherId"));
        return modelAndView;
    }

    @GetMapping("/exams/delete/{id}")
    public ModelAndView deleteExam(@PathVariable Long id, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        examService.delete(id);
        modelAndView.setViewName("redirect:http://localhost:8080/teacher-dashboard/" + session.getAttribute("teacherId"));
        return modelAndView;
    }

    @GetMapping("/exams/{id}/add-questions")
    public ModelAndView getAddQuestionsToExamPage(@PathVariable Long id, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        session.setAttribute("examId", id);
        modelAndView.setViewName("/exam/add-question-to-exam");
        return modelAndView;
    }

    @PostMapping("/exams/add-questions")
    public ModelAndView addQuestionsToExamPage(@RequestBody List<QuestionDto> questionsDto, ModelAndView modelAndView, HttpSession session) {
        Exam exam = examService.findById((Long) session.getAttribute("examId"));
        Set<Question> questions = new HashSet<>();

        questionsDto.remove(0);
        questionsDto.forEach(questionDto -> {
            Question question = new Question();
            question.setQuestion(questionDto.getQuestion());

            int correctAnswerIndex = 0;
            List<Answer> answers = new ArrayList<>();
            for (int i = 0; i < questionDto.getAnswers().length; i++) {
                Answer answer = new Answer().setAnswer(questionDto.getAnswers()[i]);
                answerService.save(answer);
                answers.add(answer);
                if (questionDto.getAnswers()[i].equals(questionDto.getCorrectAnswer())) {
                    correctAnswerIndex = i;
                }
            }

            question.setAnswers(answers);
            question.setCorrectAnswer(answers.get(correctAnswerIndex));
            questions.add(question);
        });

        exam.getQuestions().addAll(questions);
        examService.save(exam);

        modelAndView.setViewName("redirect:http://localhost:8080/teacher-dashboard/" + session.getAttribute("teacherId"));
        return modelAndView;
    }

    @GetMapping("/exams/{id}")
    public ModelAndView getStudentExamPage(@PathVariable Long id, ModelAndView modelAndView, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            modelAndView.setViewName("login");
            modelAndView.addObject("unidentified", true);
            return modelAndView;
        }

        modelAndView.setViewName("student/go-to-exam");
        session.setAttribute("exam", examService.findById(id));
        return modelAndView;
    }

    @GetMapping("/exams/{examId}/{studentId}")
    public Double getStudentExamMark(@PathVariable Long examId, @PathVariable Long studentId) {
        return studentExamService.getMark(examId, studentId);
    }

    @PostMapping("/exams/finish")
    public ModelAndView finishExam(@RequestBody List<StudentAnswerDto> studentAnswerDtos, ModelAndView modelAndView, HttpSession session) {
        studentAnswerDtos.remove(0);

        Exam exam = (Exam) session.getAttribute("exam");
        Long studentId = (Long) session.getAttribute("studentId");
        Student student = studentService.findById(studentId);

        StudentExam studentExam = new StudentExam();
        studentExam.setStudent(student);
        studentExam.setExam(exam);
        final Double[] mark = {0D};
        studentAnswerDtos.forEach(studentAnswerDto -> {
            Answer studentAnswer = answerService.findById(studentAnswerDto.getAnswerId());
            studentExam.getAnswers().add(studentAnswer);
            if (studentAnswer.getAnswer().equals(studentAnswerDto.getCorrectAnswer())) {
                mark[0] = mark[0] + 1D;
            }
        });
        studentExam.setMark(mark[0]);

        studentExamService.saveMark(mark[0], exam.getId(), studentId);
        studentExamService.saveExamAnswers(studentId, exam.getId(), studentExam.getAnswers().stream().map(Answer::getId).collect(Collectors.toList()));

        modelAndView.setViewName("redirect:http://localhost:8080/student-dashboard/" + session.getAttribute("studentId"));
        return modelAndView;
    }
}
