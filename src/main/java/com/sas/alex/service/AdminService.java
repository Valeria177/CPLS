package com.sas.alex.service;

import com.sas.alex.model.Answer;
import com.sas.alex.repository.AnswerRepository;
import com.sas.alex.repository.InfluenceRepository;
import com.sas.alex.model.Question;
import com.sas.alex.repository.QuestionRepository;
import com.sas.alex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    InfluenceRepository influenceRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Transactional
    public boolean addQuest(String text, Integer number, Long inf_id){
        Question question = new Question();
        question.setNumber(number);
        question.setQuestionText(text);
        question.setInf(influenceRepository.getById(inf_id));

        try {
            questionRepository.save(question);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean deleteQuestion(Long id){
        Question question = questionRepository.getById(id);
        try {
            questionRepository.delete(question);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean changeTextRequest(String text, Long id){
        Question question = questionRepository.getById(id);
        question.setQuestionText(text);
        try {
            questionRepository.save(question);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public  boolean addAnswer(String text, Integer number, Integer scores){
        Answer answer = new Answer();
        answer.setAnswerText(text);
        answer.setNumber(number);
        answer.setScores(scores);

        try {
            answerRepository.save(answer);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean changeTextAnswerRequest(String text, Long id){
        Answer answer = answerRepository.getById(id);
        answer.setAnswerText(text);
        try {
            answerRepository.save(answer);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
