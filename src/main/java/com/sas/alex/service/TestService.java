package com.sas.alex.service;

import com.sas.alex.model.*;
import com.sas.alex.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TestService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AttemptRepository attemptRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AnswerQuestionRepository answerQuestionRepository;

    @Autowired
    ResultRepository resultRepository;


    //Начать
    @Transactional
    public boolean startTest(User user) {
        Attempt attempt = attemptRepository.getCurrentAttempt(user.getId());
        if (attempt != null) {
            attemptRepository.delete(attempt);
        }
        attempt = new Attempt();
        attempt.setFinished(false);
        attempt.setUser(user);
        try {
            attemptRepository.save(attempt);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    //Закончить тест
    @Transactional
    public Long finishTest(User user) {
        Attempt attempt = attemptRepository.getCurrentAttempt(user.getId());
        if (attempt != null && attempt.getAnswerQuestions().size() == questionRepository.count()) {
            attempt.setFinished(true);
            attemptRepository.save(attempt);
            return attempt.getId();
        } else
            return null;
    }

    @Transactional
    public List<Attempt> getUserAttempts(User user) {
        return attemptRepository.getAllUserAttempt(user.getId());
    }


    //Главная логика, баллы и прочее, я ничего не делал. Только итоговый вывод. Но он, скорее всего, крашнется
    @Transactional
    public Result getResults(Integer scores, Long id) {

        Attempt attempt = attemptRepository.getById(id);
        Result result;
        String description = "";

        if (attempt.isFinished()) {

            if (scores <= 23) {
                description = "Ваш общий результат показывает, что у вас нет склонности к агрессии. Такие люли легко находят общий язык с разными людьми и редко выходят из себя.";
            } else if (scores <= 46) {
                description = "Ваш общий результат показывает, что у вас нет склонности к агрессии. Но иногда такие люди имеют настороженное и недоверчивое отношение к миру.";
            } else if (scores <= 69) {
                description = "Ваш общий результат показывает Вашу склонность к враждебности. Вспыльчивость определяет частоту, силу и продолжительность гнева человека. Люди с высокими показателями данного аспекта легко выходят из себя. Их гнев нередко сопровождается состоянием аффекта, который включает в себя изменения не только эмоционального, но и физического состояния.";
            } else if (scores <= 92) {
                description = "Ваш общий результат показывает Вашу склонность к враждебности. Люди с высокими показателями враждебности имеют циничное, настороженное и недоверчивое отношение к миру. Они легко обвиняют других в неискренних намерениях и лжи. Они часто пытаются распознать гнев или неискренние отношение других, но не обязательно испытывают гнев сами.";
            } else if (scores <= 115) {
                description = "Ваш общий результат показывает Вашу склонность к гневу. Люди с высокими показателями внутреннего состояния часто испытывают чувство вины из-за выражения своего гнева, но часто могут также обвинять себя, что им не удалось показать своего раздражения. Как результат, у них нередко возникают проблемы с границами в отношениях с другими людьми.";
            }


            String des = description;

            result = resultRepository.findByDescription(des);
            attempt.setResults(result);
            attemptRepository.save(attempt);
            return result;
        } else {
            return null;
        }


    }


    @Transactional
    public boolean sendAnswer(User user, Long idQ, Long idA) {
        Question question = questionRepository.getById(idQ);

        Answer answer = answerRepository.getById(idA);
        Attempt attempt = attemptRepository.getCurrentAttempt(user.getId());
        AnswerQuestion answerQuestion = new AnswerQuestion();
        answerQuestion.setQuestion(question);
        answerQuestion.setAnswer(answer);
        answerQuestion.setAttempt(attempt);

        try {
            answerQuestionRepository.save(answerQuestion);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //Список вопросов
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    //Список ответов
    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }


}
