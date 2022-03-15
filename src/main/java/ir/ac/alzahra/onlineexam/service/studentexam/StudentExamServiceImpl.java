package ir.ac.alzahra.onlineexam.service.studentexam;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class StudentExamServiceImpl implements StudentExamService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveMark(Double mark, Long examId, Long studentId) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO student_exams (mark, exam_id, student_id) " +
                        "VALUES (:mark, :examId, :studentId)");
        query.setParameter("mark", mark);
        query.setParameter("examId", examId);
        query.setParameter("studentId", studentId);
        query.executeUpdate();
    }

    @Override
    public void saveExamAnswers(Long studentId, Long examId, List<Long> answerIds) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO student_exam_answers (student_id, exam_id, answer_id) " +
                        "VALUES (:studentId, :examId, :answerId)");
        answerIds.forEach(answerId -> {
            query.setParameter("studentId", studentId);
            query.setParameter("examId", examId);
            query.setParameter("answerId", answerId);
            query.executeUpdate();
        });
    }

    @Override
    public Double getMark(Long examId, Long studentId) {
        Query query = entityManager.createNativeQuery(
                "SELECT mark " +
                        "FROM student_exams " +
                        "WHERE exam_id = :examId AND student_id = :studentId");
        query.setParameter("examId", examId);
        query.setParameter("studentId", studentId);
        List resultList = query.getResultList();
        return resultList.size() > 0? (Double) resultList.get(0) : -1D;
    }
}
