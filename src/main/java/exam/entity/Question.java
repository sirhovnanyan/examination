package exam.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Question {
    private Long id;

    private String text;

    private String answer;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;


    public Question(Long id, String text, String answer) {
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.createdOn = LocalDateTime.now();
        this.updatedOn = null;
    }

    public Question(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong("id");
        this.text = resultSet.getString("text");
        this.answer = resultSet.getString("answer");
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

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
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

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
