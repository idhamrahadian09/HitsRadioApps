package com.apps.idhamrahadian.hitsradioapps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    List<ProgramModel> result;
    @SerializedName("status_code")
    private String statusCode;
    @SerializedName("message")
    private String message;

    public List<ProgramModel> getResult(){
        return result;
    }

    public void setResult(List<ProgramModel> result){this.result = result;}

    public ResponseModel(String statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() { return statusCode;}

    public String getMessage() { return message;}
}
