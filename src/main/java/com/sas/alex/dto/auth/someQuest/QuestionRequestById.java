package com.sas.alex.dto.auth.someQuest;

public class QuestionRequestById {
    private Long id;

    public QuestionRequestById(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
