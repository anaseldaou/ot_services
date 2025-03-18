package com.nahda.ot_services.dao;

import com.nahda.ot_services.dto.LookupDTO;

public class GuardianRequestDAO extends IndividualRequestDAO {
    private boolean isFather;
    private boolean isMother;
    private LookupDTO relationship;

    public boolean isFather() {
        return isFather;
    }

    public void setFather(boolean father) {
        isFather = father;
    }

    public boolean isMother() {
        return isMother;
    }

    public void setMother(boolean mother) {
        isMother = mother;
    }

    public LookupDTO getRelationship() {
        return relationship;
    }

    public void setRelationship(LookupDTO relationship) {
        this.relationship = relationship;
    }
}
