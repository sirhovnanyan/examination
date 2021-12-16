package exam.questionservice;

import exam.entity.Question;
import exam.entity.QuestionCreationParameters;
import exam.exception.QuestionNotFound;
import exam.properties.ConnectionParameters;
import exam.utils.IOUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class DbQuestionService implements QuestionService {

    private static final String CREATE_NEW_QUESTION = "insert into questions(id, text, answer, created_on)" +
            "values (?, ?, ?, ?)";

    private static final String UPDATE_QUESTION = "update questions set text = ?, answer = ? where id = ?";

    private static final String FIND_BY_ID = "select * from questions where id = ?";

    private static final String SEARCH_SQL = "select * from questions where text = ? or answer = ?";

    private final ConnectionParameters properties = new ConnectionParameters();

    private final AtomicLong questionIdGenerator;

    public DbQuestionService() {
        this.questionIdGenerator = new AtomicLong(getMaxId());
    }

    @Override
    public Long create(QuestionCreationParameters parameter) {
        PreparedStatement statement = null;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getUrl(),
                    properties.getUsername(), properties.getPassword());
            statement = connection.prepareStatement(CREATE_NEW_QUESTION);

            long generatedId = questionIdGenerator.incrementAndGet();

            statement.setLong(1, generatedId);
            statement.setString(2, parameter.getText());
            statement.setString(3, parameter.getAnswer());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            statement.execute();

            return generatedId;

        } catch (SQLException ex) {
            throw new IllegalStateException("Creating new question failed", ex);
        } finally {
            IOUtils.closeAll(statement, connection);
        }
    }

    @Override
    public boolean update(Long id, QuestionCreationParameters parameter) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getUrl(),
                    properties.getUsername(), properties.getPassword());

            statement = connection.prepareStatement(UPDATE_QUESTION);

            statement.setString(1, parameter.getText());
            statement.setString(2, parameter.getAnswer());
            statement.setLong(3, id);

            return !statement.execute();
        } catch (SQLException ex) {
            throw new IllegalStateException("Updating question failed.", ex);
        } finally {
            IOUtils.closeAll(statement, connection);
        }
    }

    @Override
    public Optional<Question> findById(Long id) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getUrl(),
                    properties.getUsername(), properties.getPassword());
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            return resultSet.next()
                    ? Optional.of(new Question(resultSet))
                    : Optional.empty();
        } catch (SQLException ex) {
            throw new IllegalStateException("Find by id failed", ex);
        } finally {
            IOUtils.closeAll(connection, resultSet, statement);
        }
    }

    @Override
    public Question getById(Long id) {
        return findById(id).orElseThrow(() -> new QuestionNotFound(id));
    }

    @Override
    public Collection<Question> search(String text, String answer) {
        Collection<Question> questions = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(properties.getUrl(),
                    properties.getUsername(), properties.getPassword());
            statement = connection.prepareStatement(SEARCH_SQL);
            statement.setString(1, text);
            statement.setString(2, answer);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                questions.add(new Question(resultSet));
            }
            return questions;
        } catch (SQLException ex) {
            throw new IllegalStateException("Searching failed.", ex);
        } finally {
            IOUtils.closeAll(connection, resultSet, statement);
        }
    }


    private Long getMaxId() {
        Statement statement = null;
        ResultSet resultSet = null;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getUrl(),
                    properties.getUsername(), properties.getPassword());

            statement = connection.createStatement();

            resultSet = statement.executeQuery("select max(id) from questions");

            return resultSet.next()
                    ? resultSet.getLong(1)
                    : 0L;

        } catch (SQLException ex) {
            throw new IllegalStateException();
        } finally {
            IOUtils.closeAll(connection, statement, resultSet);
        }
    }
}
