package exam.questionservice;

import exam.entity.Question;

import java.util.Comparator;

public class SortByCreateTime implements Comparator<Question> {

    @Override
    public int compare(Question o1, Question o2) {
        return o1.getCreatedOn().compareTo(o2.getCreatedOn());
    }
}
