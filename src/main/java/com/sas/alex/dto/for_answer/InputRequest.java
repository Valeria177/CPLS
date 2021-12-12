package com.sas.alex.dto.for_answer;

import javax.validation.constraints.NotBlank;

public class InputRequest {
    @NotBlank
    private Long idQuest;

    @NotBlank
    private Long idAnswer;

    public Long getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(Long idQuest) {
        this.idQuest = idQuest;
    }

    public Long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Long idAnswer) {
        this.idAnswer = idAnswer;
    }
}
