package com.nahda.ot_services.dao;

public class NoteLookupDAO extends LookupDAO {
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;
}
