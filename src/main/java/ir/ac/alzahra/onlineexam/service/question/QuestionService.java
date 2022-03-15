package ir.ac.alzahra.onlineexam.service.question;

import ir.ac.alzahra.onlineexam.model.data.Question;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface QuestionService {

    void save(Question question);
    void saveAll(Set<Question> questions);
}
