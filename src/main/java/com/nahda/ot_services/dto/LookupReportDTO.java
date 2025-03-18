package com.nahda.ot_services.dto;

public class LookupReportDTO {


    public LookupReportDTO(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
}
