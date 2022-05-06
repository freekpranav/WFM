package com.hoperaiser.wfm;

public class Dataclass {


    public Dataclass(){

    }

    String uid,name,hotel_name,Food_type,Food_Quantity,Food_Description,current_time,Email,Phone_no,Alt_no,Address,Time_limit,IsApproved,IsVisible,IsAvailable;

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

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getFood_type() {
        return Food_type;
    }

    public void setFood_type(String food_type) {
        Food_type = food_type;
    }

    public String getFood_Quantity() {
        return Food_Quantity;
    }

    public void setFood_Quantity(String food_Quantity) {
        Food_Quantity = food_Quantity;
    }

    public String getFood_Description() {
        return Food_Description;
    }

    public void setFood_Description(String food_Description) {
        Food_Description = food_Description;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public String getAlt_no() {
        return Alt_no;
    }

    public void setAlt_no(String alt_no) {
        Alt_no = alt_no;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTime_limit() {
        return Time_limit;
    }

    public void setTime_limit(String time_limit) {
        Time_limit = time_limit;
    }

    public String getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(String isApproved) {
        IsApproved = isApproved;
    }

    public String getIsVisible() {
        return IsVisible;
    }

    public void setIsVisible(String isVisible) {
        IsVisible = isVisible;
    }

    public String getIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        IsAvailable = isAvailable;
    }

    public Dataclass(String uid, String name, String hotel_name, String food_type, String food_Quantity, String food_Description, String current_time, String email, String phone_no, String alt_no, String address, String time_limit, String isApproved, String isVisible, String isAvailable) {
        this.uid = uid;
        this.name = name;
        this.hotel_name = hotel_name;
        Food_type = food_type;
        Food_Quantity = food_Quantity;
        Food_Description = food_Description;
        this.current_time = current_time;
        Email = email;
        Phone_no = phone_no;
        Alt_no = alt_no;
        Address = address;
        Time_limit = time_limit;
        IsApproved = isApproved;
        IsVisible = isVisible;
        IsAvailable = isAvailable;
    }
}
