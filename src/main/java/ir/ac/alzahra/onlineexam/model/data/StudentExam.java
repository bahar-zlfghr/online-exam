package ir.ac.alzahra.onlineexam.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
@Table(name = "student_exams")
public class StudentExam implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "exam_id")
    @JsonIgnore
    private Exam exam;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "student_exam_answers",
            joinColumns = {@JoinColumn(name = "student_id"), @JoinColumn(name = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id")})
    private List<Answer> answers = new ArrayList<>();

    private Double mark;

    public Student getStudent() {
        return student;
    }

    public StudentExam setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Exam getExam() {
        return exam;
    }

    public StudentExam setExam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public StudentExam setAnswers(List<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public Double getMark() {
        return mark;
    }

    public StudentExam setMark(Double mark) {
        this.mark = mark;
        return this;
    }
}
