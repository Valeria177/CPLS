package com.sas.alex.dto.some;

import com.sas.alex.model.Result;

import java.util.List;

public class ResultResponse {

    private Integer scores;

    private Result results;

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public ResultResponse(Result results) {
        this.results = results;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }
}
