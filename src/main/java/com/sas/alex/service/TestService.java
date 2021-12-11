package com.sas.alex.service;

import com.sas.alex.persist.Question;
import com.sas.alex.persist.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
