package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto;

public class TopicDto {

    private String messaggio;

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public TopicDto(String messaggio) {
        this.messaggio = messaggio;
    }
}
