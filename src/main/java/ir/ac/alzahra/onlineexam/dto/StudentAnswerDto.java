package ir.ac.alzahra.onlineexam.dto;

/**
 * @author : Bahar Zolfaghari
 **/
public class StudentAnswerDto {
    private String question;
    private Long answerId;
    private String correctAnswer;

    public String getQuestion() {
        return question;
    }

    public StudentAnswerDto setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public StudentAnswerDto setAnswerId(Long answerId) {
        this.answerId = answerId;
        return this;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public StudentAnswerDto setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}
