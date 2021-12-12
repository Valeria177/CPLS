package com.sas.alex.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attempt")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "finished")
    private boolean finished;

    @ManyToOne
    @JsonManagedReference
    private User user;



    private List<Result> results;

    private List<AnswerQuestion> answerQuests;

    public List<AnswerQuestion> getAnswerQuests() {
        return answerQuests;
    }

    public void setAnswerQuests(List<AnswerQuestion> answerQuests) {
        this.answerQuests = answerQuests;
    }

    public Attempt() {
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
