package com.sas.alex.model;


import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "answer_text",nullable = false)
    private String answerText;

    @Column(name = "scores")
    private Integer scores;


    public Answer() {
    }

    public Answer(Long id, Integer number, String answerText, Integer scores) {
        this.id = id;
        this.number = number;
        this.answerText = answerText;
        this.scores = scores;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
