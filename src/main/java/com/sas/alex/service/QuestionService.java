package com.sas.alex.service;


import com.sas.alex.model.Question;
import com.sas.alex.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> listAll(){
        return questionRepository.findAll();
    }

    public void save (Question question){
        questionRepository.save(question);
    }

    public Question getQuestion(Long id){
        return questionRepository.findById(id).get();
    }

    public  void delete(Long id){
        questionRepository.deleteById(id);
    }

    //Тут логика вывода результата, но я хз чё у пользователя счиать
    public String getResult(Integer RngShite){
        String res = "Пока хз чё тут вообще должно быть про агрессию: " + RngShite ;
        return res;
    }
}
