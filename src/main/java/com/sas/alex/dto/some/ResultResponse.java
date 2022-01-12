package com.sas.alex.dto.some;

import com.sas.alex.model.Result;

import java.util.List;

public class ResultResponse {

    private Integer scores;

    private List<Result> results;

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public ResultResponse(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
