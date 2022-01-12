package com.sas.alex.dto.some;

import com.sas.alex.model.Result;

import java.util.List;

public class ResultResponse {

    Result result;

    public ResultResponse() {
    }

    public ResultResponse(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
