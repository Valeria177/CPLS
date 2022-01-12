package com.sas.alex.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne  (fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "attempt_id")
    private Result results;

    @OneToMany (fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "attempt_id")
    @JsonBackReference
    private List<AnswerQuestion> answerQuestions;

    public Attempt() {
    }

    public List<AnswerQuestion> getAnswerQuestions() {
        return answerQuestions;
    }

    public void setAnswerQuestions(List<AnswerQuestion> answerQuestions) {
        this.answerQuestions = answerQuestions;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
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