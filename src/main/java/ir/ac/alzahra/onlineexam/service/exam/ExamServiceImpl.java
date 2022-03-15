package ir.ac.alzahra.onlineexam.service.exam;

import ir.ac.alzahra.onlineexam.model.data.Exam;
import ir.ac.alzahra.onlineexam.model.repository.ExamRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void delete(Long id) {
        examRepository.deleteExam(id);
    }

    @Override
    public void stopExam(Long id) {
        Exam exam = examRepository.getById(id);
        exam.setStopped(Boolean.TRUE);
        examRepository.save(exam);
    }

    @Override
    public Exam findById(Long id) {
        return examRepository.findById(id).get();
    }

    @Override
    public Set<Exam> findByCourseId(Long courseId) {
        return examRepository.findByCourseId(courseId);
    }
}
