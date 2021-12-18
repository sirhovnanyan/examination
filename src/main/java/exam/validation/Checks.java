package exam.validation;

import exam.entity.QuestionCreationParameters;

public class Checks {
    private Checks() {
        throw new UnsupportedOperationException();
    }

    public static void checkParameter(QuestionCreationParameters parameter) {
        if (parameter == null || parameter.getText() == null || parameter.getAnswer() == null ||
                parameter.getText().length() == 0 || parameter.getAnswer().length() == 0) {
            throw new IllegalArgumentException("Passed incorrect value.");
        }
    }

    public static void checkStr(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Passed incorrect value for string.");
        }
    }

    public static void checkId(Long id) {
        if (id == 0 || id <= 0) {
            throw new IllegalArgumentException("Passed invalid value for 'id'.");
        }
    }
}
