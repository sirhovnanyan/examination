package exam;

import exam.entity.Question;
import exam.entity.QuestionCreationParameters;
import exam.questionservice.DbQuestionService;
import exam.questionservice.HashMapQuestionServiceImpl;
import exam.questionservice.TreeMapQuestionServiceImpl;

import java.util.Collection;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        QuestionCreationParameters parameter1 = new QuestionCreationParameters("aaa", "bbb");
        QuestionCreationParameters parameter2 = new QuestionCreationParameters("bbb", "aaa");
        QuestionCreationParameters parameter3 = new QuestionCreationParameters("ccc", "ddd");
        QuestionCreationParameters parameter4 = new QuestionCreationParameters("eee", "fff");
        QuestionCreationParameters parameter5 = new QuestionCreationParameters("ggg", "hhh");
        QuestionCreationParameters parameter6 = new QuestionCreationParameters("iii", "jjj");
        QuestionCreationParameters parameter7 = new QuestionCreationParameters("kkk", "lll");

// HashMap Implementation testing
        HashMapQuestionServiceImpl questionServiceHashMap = new HashMapQuestionServiceImpl();

        questionServiceHashMap.create(parameter1);
        questionServiceHashMap.create(parameter2);
        questionServiceHashMap.create(parameter3);
        questionServiceHashMap.create(parameter4);
        questionServiceHashMap.create(parameter5);
        questionServiceHashMap.create(parameter6);
        System.out.println(questionServiceHashMap);

        questionServiceHashMap.update(1L, parameter2);

        Optional<Question> find = questionServiceHashMap.findById(6L);

        Question getQuestion = questionServiceHashMap.getById(18L);

        Collection<Question> questions = questionServiceHashMap.search("bbb", "aaa");

        System.out.println(questionServiceHashMap);


//TreeMap Implementation testing
        TreeMapQuestionServiceImpl treeMapQuestionService = new TreeMapQuestionServiceImpl();

        treeMapQuestionService.create(parameter1);
        treeMapQuestionService.create(parameter2);
        treeMapQuestionService.create(parameter3);
        treeMapQuestionService.create(parameter4);
        treeMapQuestionService.create(parameter5);
        treeMapQuestionService.create(parameter6);
        //treeMapQuestionService.create(parameter7);

        treeMapQuestionService.update(1L, parameter7);

        Optional<Question> opQuestion = treeMapQuestionService.findById(6L);

        Question question = treeMapQuestionService.getById(8L);

        Collection<Question> questions1 = treeMapQuestionService.search("aaa", "aaa");
        System.out.println();


//DB Implementation testing
        DbQuestionService dbQuestionService = new DbQuestionService();
        dbQuestionService.create(parameter1);
        dbQuestionService.create(parameter2);
        dbQuestionService.create(parameter3);
        dbQuestionService.create(parameter4);
        dbQuestionService.create(parameter5);
        dbQuestionService.create(parameter6);

        dbQuestionService.update(6L, parameter6);

        Optional<Question> optionalQuestion = dbQuestionService.findById(6L);

        Question question1 = dbQuestionService.getById(3L);

        Collection<Question> questions2 = dbQuestionService.search("aaa", "aaa");

        dbQuestionService.create(parameter7);

        System.out.println(55);
    }
}
