package ir.ac.alzahra.onlineexam.dto;

/**
 * @author : Bahar Zolfaghari
 **/
public class QuestionDto {

    private String question;
    private String[] answers = new String[4];
    private String correctAnswer;

    public String getQuestion() {
        return question;
    }

    public QuestionDto setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String[] getAnswers() {
        return answers;
    }

    public QuestionDto setAnswers(String[] answers) {
        this.answers = answers;
        return this;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionDto setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}
