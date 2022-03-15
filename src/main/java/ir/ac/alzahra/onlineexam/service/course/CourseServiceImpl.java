package ir.ac.alzahra.onlineexam.service.course;

import ir.ac.alzahra.onlineexam.model.data.Course;
import ir.ac.alzahra.onlineexam.model.data.EducationalUser;
import ir.ac.alzahra.onlineexam.model.repository.CourseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Set<Course> findAll() {
        return new HashSet<>(courseRepository.findAll());
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Course> findByEducationalUserId(Long teacherId) {
        return courseRepository.findAll().stream().filter(course -> course.getEducationalUsers().stream()
                .map(EducationalUser::getId).collect(Collectors.toList()).contains(teacherId)).collect(Collectors.toSet());
    }

    @Override
    public Set<EducationalUser> findEducationalUsers(Long id) {
        return new HashSet<>(Objects.requireNonNull(courseRepository.findById(id).orElse(null)).getEducationalUsers());
    }
}
