package ir.ac.alzahra.onlineexam.service.exam;

import ir.ac.alzahra.onlineexam.model.data.Exam;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ExamService {

    void save(Exam exam);
    void delete(Long id);
    void stopExam(Long id);
    Exam findById(Long id);
    Set<Exam> findByCourseId(Long courseId);
}
