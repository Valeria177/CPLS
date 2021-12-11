package com.sas.alex.dto.auth.someQuest;

import com.sas.alex.persist.Question;

import java.util.List;

public class QuestionResponse {
    private List<Question> question;

    public QuestionResponse(List<Question> question){
        this.question = question;
    }

    public List<Question> getQuestion(){
        return question;
    }

    public void setQuestion(List<Question> question){
        this.question = question;
    }
}
