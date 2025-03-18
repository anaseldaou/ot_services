package com.nahda.ot_services.dao;


import java.util.UUID;

public class RepeatedClassDAO {

    private UUID uuid;

    public LookupDAO getClasse() {
        return classe;
    }

    public void setClasse(LookupDAO classe) {
        this.classe = classe;
    }

    private LookupDAO classe;
    private int times;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
