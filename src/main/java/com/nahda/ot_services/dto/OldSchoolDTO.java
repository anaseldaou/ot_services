package com.nahda.ot_services.dto;

public class OldSchoolDTO {
    public LookupDTO getSchool() {
        return school;
    }

    public void setSchool(LookupDTO school) {
        this.school = school;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    private LookupDTO school;
    private String leaveReason;

}
