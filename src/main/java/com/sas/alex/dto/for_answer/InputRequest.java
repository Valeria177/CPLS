package com.sas.alex.dto.for_answer;

import javax.validation.constraints.NotBlank;

public class InputRequest {

    @NotBlank
    private Long idQ;

    @NotBlank
    private Long idA;

    public Long getIdQ() {
        return idQ;
    }

    public void setIdQ(Long idQ) {
        this.idQ = idQ;
    }

    public Long getIdA() {
        return idA;
    }

    public void setIdA(Long idA) {
        this.idA = idA;
    }
}
