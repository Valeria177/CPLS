package com.sas.alex.dto.admin;

public class ChangeTextRequest {

    private String text;
    private Long id;

    public ChangeTextRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
