package com.example.marketofsecondhandmaterials;

import com.google.firebase.database.Exclude;

public class Booking {

    private String Name;
    private String Code;
    private String Phone;
    private String Location;
    private static String mKey;

    public Booking() {

    }
    public Booking(String name, String code, String phone, String location) {
        if(name.trim().equals("")){
            name="no name";
        }


        Name = name;
        Code = code;
        Phone = phone;
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getPhone() {
        return Phone;
    }

    public void setLocation(String location) {Location= location; }
    public String getLocation() {
        return Location;
    }

    @Exclude
    public String getKey(){
        return mKey;

    }
    @Exclude
    public static void setKey(String key){
        mKey = key;
    }

}