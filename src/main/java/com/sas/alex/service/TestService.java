package com.sas.alex.service;

import com.sas.alex.model.Question;
import com.sas.alex.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    /*public List<Question> getQuestionById (Long id){
        Long count = questionRepository.count();
        return questionRepository.findById(id);
    }*/
}
