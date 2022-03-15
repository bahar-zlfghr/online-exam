package ir.ac.alzahra.onlineexam.service.course;

import ir.ac.alzahra.onlineexam.model.data.Course;
import ir.ac.alzahra.onlineexam.model.data.EducationalUser;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CourseService {

    void save(Course course);
    void delete(Long id);
    Set<Course> findAll();
    Course findById(Long id);
    Set<Course> findByEducationalUserId(Long teacherId);
    Set<EducationalUser> findEducationalUsers(Long id);
}
