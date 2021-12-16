package exam.questionservice;

import exam.entity.Question;
import exam.entity.QuestionCreationParameters;
import exam.exception.QuestionNotFound;

import java.time.LocalDateTime;
import java.util.*;

public class HashMapQuestionServiceImpl implements QuestionService {

    private final Map<Long, Question> data;

    private Long id;

    public HashMapQuestionServiceImpl() {
        this.data = new HashMap<>();
        this.id = 0L;
    }

    @Override
    public Long create(QuestionCreationParameters parameter) {
        id++;
        data.put(id, new Question(id, parameter.getText(), parameter.getAnswer()));
        return id;
    }

    @Override
    public boolean update(Long id, QuestionCreationParameters parameter) {
        Question question;
        if ((question = data.get(id)) != null) {
            question.setText(parameter.getText());
            question.setAnswer(parameter.getAnswer());
            question.setUpdatedOn(LocalDateTime.now());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Question> findById(Long id) {
        Question question;
        if ((question = data.get(id)) != null) {
            return Optional.of(question);
        }
        return Optional.empty();
    }

    @Override
    public Question getById(Long id) {
        return findById(id).orElseThrow(() -> new QuestionNotFound(id));
    }

    @Override
    public Collection<Question> search(String text, String answer) {
        Collection<Question> questions = new ArrayList<>();
        Question question;
        for (long i : data.keySet()) {
            question = data.get(i);
            if (question.getText().equals(text) ||
                    question.getAnswer().equals(answer)) {
                questions.add(question);
            }
        }
        return questions;
    }
}
