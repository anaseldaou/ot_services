package com.nahda.ot_services.dto;

public class RepeatedClassDTO {

    private LookupDTO classe;
    private int times;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public LookupDTO getClasse() {
        return classe;
    }

    public void setClasse(LookupDTO classe) {
        this.classe = classe;
    }
}
