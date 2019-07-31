package com.apps.idhamrahadian.hitsradioapps.model;

import com.google.gson.annotations.SerializedName;

public class ProgramModel {

    @SerializedName("prog_name")
    private String prog_name;
    @SerializedName("prog_desc")
    private String prog_desc;
    @SerializedName("prog_hours")
    private String prog_hours;
    @SerializedName("prog_day")
    private String prog_day;

    public String getProg_name() {
        return prog_name;
    }

    public void setProg_name(String prog_name) {
        this.prog_name = prog_name;
    }

    public String getProg_desc() {
        return prog_desc;
    }

    public void setProg_desc(String prog_desc) {
        this.prog_desc = prog_desc;
    }

    public String getProg_hours() {
        return prog_hours;
    }

    public void setProg_hours(String prog_hours) {
        this.prog_hours = prog_hours;
    }

    public String getProg_day() {
        return prog_day;
    }

    public void setProg_day(String prog_day) {
        this.prog_day = prog_day;
    }
}
