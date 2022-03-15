package ir.ac.alzahra.onlineexam.service.answer;

import ir.ac.alzahra.onlineexam.model.data.Answer;
import ir.ac.alzahra.onlineexam.model.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Answer findById(Long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }
}
