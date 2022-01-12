package com.sas.alex.service;

import com.sas.alex.model.*;
import com.sas.alex.repository.AnswerQuestionRepository;
import com.sas.alex.repository.AnswerRepository;
import com.sas.alex.repository.AttemptRepository;
import com.sas.alex.repository.QuestionRepository;
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


    //Главная логика, баллы и прочее, я ничего не делал. Только итоговый вывод. Но он, скорее всего, крашнется
    @Transactional
    public Result results(Long id, int scores){
        Attempt attempt = attemptRepository.getById(id);
        Result result = new Result();

        if(attempt.isFinished()){

            if(scores>=23){
                result.setDescription("Ну вы того этого, пассивный врушка!");
            }


            attempt.setResults(result);
            attemptRepository.save(attempt);
            return result;

        }
        return null;
    }



    @Transactional
    public boolean sendAnswer(User user, Long idQ, Long idA){
        Question question = questionRepository.getById(idQ);

        Answer answer = answerRepository.getById(idA);
        Attempt attempt = attemptRepository.getCurrentAttempt(user.getId());
        AnswerQuestion answerQuestion = new AnswerQuestion();
        answerQuestion.setQuestion(question);
        answerQuestion.setAnswer(answer);
        answerQuestion.setAttempt(attempt);

        try {
            answerQuestionRepository.save(answerQuestion);
        }catch (Exception e){
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
