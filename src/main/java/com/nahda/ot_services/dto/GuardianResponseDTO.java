package com.nahda.ot_services.dto;

public class GuardianResponseDTO extends  IndividualResponseDTO {
    private boolean isFather;
    private boolean isMother;

    public boolean getIsFather() {
        return isFather;
    }

    public void setIsFather(boolean father) {
        isFather = father;
    }

    public boolean getIsMother() {
        return isMother;
    }

    public void setIsMother(boolean mother) {
        isMother = mother;
    }
}
