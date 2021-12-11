package com.sas.alex.dto.admin;

public class QuestionCreateRequest {
    private Integer number;
    private String text;
    private Long inf_id;

    public QuestionCreateRequest() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getInf_id() {
        return inf_id;
    }

    public void setInf_id(Long inf_id) {
        this.inf_id = inf_id;
    }
}


