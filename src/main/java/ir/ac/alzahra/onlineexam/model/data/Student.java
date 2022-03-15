package ir.ac.alzahra.onlineexam.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
@DiscriminatorValue(value = "student")
public class Student extends EducationalUser {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<StudentExam> exams = new ArrayList<>();

    public List<StudentExam> getExams() {
        return exams;
    }

    public Student setExams(List<StudentExam> exams) {
        this.exams = exams;
        return this;
    }
}
