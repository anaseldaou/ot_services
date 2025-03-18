package com.nahda.ot_services.dto;

import java.util.UUID;

public class LookupDTO {

    UUID uuid;

    public LookupDTO(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public LookupDTO(UUID uuid) {
        this.uuid = uuid;
    }

    public LookupDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
