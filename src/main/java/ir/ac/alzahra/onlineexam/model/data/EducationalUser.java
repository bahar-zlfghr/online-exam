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
public class EducationalUser extends User implements Serializable {

    private Boolean emailVerification = Boolean.FALSE;
    private Boolean adminVerification = Boolean.FALSE;

    @ManyToMany(targetEntity = Course.class, cascade = {CascadeType.MERGE})
    @JoinTable(name = "educational_user_courses",
            joinColumns = @JoinColumn(name = "educational_user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    public Boolean isEmailVerification() {
        return emailVerification;
    }

    public EducationalUser setEmailVerification(Boolean emailVerification) {
        this.emailVerification = emailVerification;
        return this;
    }

    public Boolean isAdminVerification() {
        return adminVerification;
    }

    public EducationalUser setAdminVerification(Boolean adminVerification) {
        this.adminVerification = adminVerification;
        return this;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public EducationalUser setCourses(List<Course> courses) {
        this.courses = courses;
        return this;
    }
}
