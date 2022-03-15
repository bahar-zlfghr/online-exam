package ir.ac.alzahra.onlineexam.service.studentexam;

import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
public interface StudentExamService {

    void saveMark(Double mark, Long examId, Long studentId);
    void saveExamAnswers(Long studentId, Long examId, List<Long> answerIds);
    Double getMark(Long examId, Long studentId);
}
