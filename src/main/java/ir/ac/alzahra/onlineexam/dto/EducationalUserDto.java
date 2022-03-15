package ir.ac.alzahra.onlineexam.dto;

import ir.ac.alzahra.onlineexam.model.data.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
public class EducationalUserDto {
    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public EducationalUserDto setCourses(List<Course> courses) {
        this.courses = courses;
        return this;
    }
}
