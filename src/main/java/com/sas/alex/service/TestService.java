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
    /*@Transactional
    public List<Result> results(Long id){
        Attempt attempt = attemptRepository.getById(id);
        List<Result> results = new ArrayList<>();

        if(attempt.isFinished()){

            attempt.setResults(results);
            attemptRepository.save(attempt);
            return results;
        }
        return null;
    }

    public List<Question> getQuestionById (Long id){
        Long count = questionRepository.count();
        return questionRepository.findById(id);
    }*/


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
