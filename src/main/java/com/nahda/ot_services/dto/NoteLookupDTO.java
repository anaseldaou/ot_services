package com.nahda.ot_services.dto;

public class NoteLookupDTO extends LookupDTO {
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;
}
