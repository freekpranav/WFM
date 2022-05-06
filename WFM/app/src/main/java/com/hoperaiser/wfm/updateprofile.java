package com.hoperaiser.wfm;

public class updateprofile {


    public updateprofile(){

    }

    String uid,name,address,email,phone,alt_no,hot_org_name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlt_no() {
        return alt_no;
    }

    public void setAlt_no(String alt_no) {
        this.alt_no = alt_no;
    }

    public String getHot_org_name() {
        return hot_org_name;
    }

    public void setHot_org_name(String hot_org_name) {
        this.hot_org_name = hot_org_name;
    }

    public updateprofile(String uid, String name, String address, String email, String phone, String alt_no, String hot_org_name) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.alt_no = alt_no;
        this.hot_org_name = hot_org_name;
    }
}
