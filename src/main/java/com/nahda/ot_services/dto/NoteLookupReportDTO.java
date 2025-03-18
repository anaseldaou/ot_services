package com.nahda.ot_services.dto;

public class NoteLookupReportDTO extends LookupReportDTO {

    public NoteLookupReportDTO(String name, String note) {
        super(name);
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;

}
