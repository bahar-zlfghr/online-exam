package ir.ac.alzahra.onlineexam.model.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "question_answers",
            joinColumns = {@JoinColumn(name = "question_id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id")})
    private List<Answer> answers = new ArrayList<>();

    @OneToOne(targetEntity = Answer.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "correct_answer_id")
    private Answer correctAnswer;

    public Long getId() {
        return id;
    }

    public Question setId(Long id) {
        this.id = id;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public Question setQuestion(String question) {
        this.question = question;
        return this;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Question setAnswers(List<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public Question setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}
