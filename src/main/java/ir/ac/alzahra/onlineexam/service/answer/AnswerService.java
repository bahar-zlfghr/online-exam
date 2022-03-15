package ir.ac.alzahra.onlineexam.service.answer;

import ir.ac.alzahra.onlineexam.model.data.Answer;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AnswerService {

    void save(Answer answer);
    Answer findById(Long answerId);
}
