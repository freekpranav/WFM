package com.hoperaiser.wfm;

public class AddAdmin {

    public AddAdmin(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddAdmin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    String email,password;
}
