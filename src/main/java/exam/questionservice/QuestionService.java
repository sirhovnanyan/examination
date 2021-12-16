package exam.questionservice;

import exam.entity.Question;
import exam.entity.QuestionCreationParameters;

import java.util.Collection;
import java.util.Optional;

public interface QuestionService {

    Long create(QuestionCreationParameters parameter);

    boolean update(Long id, QuestionCreationParameters parameter);

    Optional<Question> findById(Long id);

    Question getById(Long id);

    Collection<Question> search(String text, String answer);

}
