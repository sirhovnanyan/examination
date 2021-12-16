package exam.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Question extends QuestionCreationParameters {
    private Long id;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;


    public Question(Long id, String text, String answer) {
        super(text, answer);
        this.id = id;
        this.createdOn = LocalDateTime.now();
        this.updatedOn = null;
    }

    public Question(ResultSet resultSet) throws SQLException {
        super(resultSet.getString("text"), resultSet.getString("answer"));
        this.id = resultSet.getLong("id");
        this.createdOn = resultSet.getTimestamp("created_on").toLocalDateTime();

        Timestamp updatedOn = resultSet.getTimestamp("updated_on");
        if (updatedOn != null) {
            this.updatedOn = updatedOn.toLocalDateTime();
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
