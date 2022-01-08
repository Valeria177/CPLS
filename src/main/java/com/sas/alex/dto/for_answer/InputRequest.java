package com.sas.alex.dto.for_answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InputRequest {

    private Long idQ;

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
