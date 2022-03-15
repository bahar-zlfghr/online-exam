package ir.ac.alzahra.onlineexam.service.question;

import ir.ac.alzahra.onlineexam.model.data.Question;
import ir.ac.alzahra.onlineexam.model.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void saveAll(Set<Question> questions) {
        questionRepository.saveAll(questions);
    }
}
