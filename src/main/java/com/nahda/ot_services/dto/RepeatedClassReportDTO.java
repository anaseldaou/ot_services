package com.nahda.ot_services.dto;

public class RepeatedClassReportDTO {

    public RepeatedClassReportDTO(String classe, int times) {
        this.classe = classe;
        this.times = times;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    private String classe;
    private int times;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
