package com.nahda.ot_services.dao;

public class OldSchoolDAO {
    public LookupDAO getSchool() {
        return school;
    }

    public void setSchool(LookupDAO school) {
        this.school = school;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    private LookupDAO school;
    private String leaveReason;
}
