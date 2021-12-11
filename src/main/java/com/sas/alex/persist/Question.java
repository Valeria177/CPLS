package com.sas.alex.persist;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Integer number;

    @NotBlank
    @Column(nullable = false)
    private String questionText;

    @ManyToOne
    private Influence inf;

    public Question() {
    }

    public Question(Long id, Integer number, @NotBlank String questionTitle, @NotBlank String questionText, Influence inf) {
        this.id = id;
        this.number = number;
        this.questionText = questionText;
        this.inf = inf;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Influence getInf() {
        return inf;
    }

    public void setInf(Influence inf) {
        this.inf = inf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
