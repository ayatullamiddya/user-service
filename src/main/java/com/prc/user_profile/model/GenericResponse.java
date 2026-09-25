package com.prc.user_profile.model;

public class GenericResponse {
    private String errorMsg;
    private boolean success;
    public GenericResponse(String errorMsg){
        this.errorMsg = errorMsg;
        this.success = false;
    }
}
