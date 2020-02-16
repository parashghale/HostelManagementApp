package com.parash.hostelmanagementapp.Model;

public class Student {
    private String name;
    private String location;
    private String email;
    private String phone;
    private String id;
    private String room;
    private String roomRent;

    public String getName() {
        return name;
    }

    public Student() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student(String name, String location, String email, String phone, String id, String room, String roomRent) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.room = room;
        this.roomRent = roomRent;
    }

    public String getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(String roomRent) {
        this.roomRent = roomRent;
    }

    public String getFullname() {
        return name;
    }

    public void setFullname(String fullname) {
        this.name = fullname;
    }

    public String getLoction() {
        return location;
    }

    public void setLoction(String location) {
        this.location = location;
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
