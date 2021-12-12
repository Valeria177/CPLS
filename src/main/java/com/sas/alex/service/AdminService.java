package com.sas.alex.service;

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
}