package ir.ac.alzahra.onlineexam.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer duration;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    private Boolean stopped;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<StudentExam> students = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "exam_questions",
            joinColumns = {@JoinColumn(name = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private Set<Question> questions = new HashSet<>();

    @Transient
    private Double mark;

    public Double getMark() {
        return mark;
    }

    public Exam setMark(Double mark) {
        this.mark = mark;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Exam setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Exam setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Exam setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Exam setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public Exam setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public Exam setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Boolean getStopped() {
        return stopped;
    }

    public Exam setStopped(Boolean stopped) {
        this.stopped = stopped;
        return this;
    }

    public Course getCourse() {
        return course;
    }

    public Exam setCourse(Course course) {
        this.course = course;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Exam setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public List<StudentExam> getStudents() {
        return students;
    }

    public Exam setStudents(List<StudentExam> students) {
        this.students = students;
        return this;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Exam setQuestions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }
}
