package com.sas.alex.dto.admin;

public class AnswerCreateRequest {
    private String text;
    private Integer number;
    private Integer scores;

    public AnswerCreateRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }
}
