package com.parash.hostelmanagementapp.Model;

public class Student {
    private String fullname;
    private String loction;
    private String email;
    private String phone;
    private String room;

    public Student(String fullname, String loction, String email, String phone, String room) {
        this.fullname = fullname;
        this.loction = loction;
        this.email = email;
        this.phone = phone;
        this.room = room;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLoction() {
        return loction;
    }

    public void setLoction(String loction) {
        this.loction = loction;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
