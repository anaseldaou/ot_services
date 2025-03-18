package com.nahda.ot_services.dto;

public class OldSchoolReportDTO {

    private String school;
    private String leaveReason;

    public OldSchoolReportDTO(String school, String leaveReason) {
        this.school = school;
        this.leaveReason = leaveReason;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }
}
