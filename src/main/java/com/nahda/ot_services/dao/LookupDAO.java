package com.nahda.ot_services.dao;

import java.util.UUID;

public class LookupDAO {
    UUID uuid;
    String name;

    public LookupDAO(){}

    public LookupDAO(UUID uuid){
        this.uuid = uuid;
    }

    public LookupDAO(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
