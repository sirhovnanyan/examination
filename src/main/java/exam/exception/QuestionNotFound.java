package exam.exception;

public class QuestionNotFound extends RuntimeException {
    private final Long questionId;

    public QuestionNotFound(Long questionId) {
        System.out.println("Question not found: " + questionId);
        this.questionId = questionId;
    }
}
