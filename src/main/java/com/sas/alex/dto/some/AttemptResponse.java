package com.sas.alex.dto.some;


import com.sas.alex.model.Attempt;

import java.util.List;

public class AttemptResponse {

    private List<Attempt> attempts;

    public AttemptResponse(List<Attempt> attempts) {
        this.attempts = attempts;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }
}
