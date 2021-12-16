package exam.questionservice;

import exam.entity.Question;
import exam.entity.QuestionCreationParameters;
import exam.exception.QuestionNotFound;

import java.time.LocalDateTime;
import java.util.*;

public class TreeMapQuestionServiceImpl implements QuestionService {

    public final Map<Question, Long> data;

    private Long id;

    public TreeMapQuestionServiceImpl() {
        this.data = new TreeMap<>(new SortByCreateTime());
        this.id = 0L;
    }

    @Override
    public Long create(QuestionCreationParameters parameter) {
        this.id++;
        data.put(new Question(id, parameter.getText(), parameter.getAnswer()), id);
        return id;
    }

    @Override
    public boolean update(Long id, QuestionCreationParameters parameter) {
        for (Question question : data.keySet()) {
            if (question.getId() == id) {
                question.setText(parameter.getText());
                question.setAnswer(parameter.getAnswer());
                question.setUpdatedOn(LocalDateTime.now());

                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Question> findById(Long id) {
        for (Question question : data.keySet()) {
            if (question.getId() == id) {
                return Optional.of(question);
            }
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
        for (Question question : data.keySet()) {
            if (question.getText().equals(text) ||
                    question.getAnswer().equals(answer)) {
                questions.add(question);
            }
        }
        return questions;
    }
}
