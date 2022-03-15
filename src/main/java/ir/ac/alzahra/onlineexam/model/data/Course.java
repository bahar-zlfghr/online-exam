package ir.ac.alzahra.onlineexam.model.data;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{not.empty}")
    @Size(min = 3, max = 25, message = "{size}")
    private String title;

    @NotEmpty(message = "{not.empty}")
    @Size(min = 3, max = 25, message = "{size}")
    private String category;

    @ManyToMany(mappedBy = "courses", targetEntity = EducationalUser.class, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<EducationalUser> educationalUsers = new HashSet<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Exam> exams = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Course setCategory(String category) {
        this.category = category;
        return this;
    }

    public Set<EducationalUser> getEducationalUsers() {
        return educationalUsers;
    }

    public Course setEducationalUsers(Set<EducationalUser> educationalUsers) {
        this.educationalUsers = educationalUsers;
        return this;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public Course setExams(Set<Exam> exams) {
        this.exams = exams;
        return this;
    }
}
