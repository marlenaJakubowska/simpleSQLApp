package com.codecool.simpleSQLApp.models;

public class Mentor extends User {

    private String nickName;
    private String city;
    private Integer favouriteNumber;

    public Mentor(String firstName, String lastName, String phoneNumber, String email, String nickName, String city, int favouriteNumber){
        super(firstName, lastName, phoneNumber, email);
        this.nickName = nickName;
        this.city = city;
        this.favouriteNumber = favouriteNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public Mentor setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Mentor setCity(String city) {
        this.city = city;
        return this;
    }

    public Integer getFavouriteNumber() {
        return favouriteNumber;
    }

    public Mentor setFavouriteNumber(Integer favouriteNumber) {
        this.favouriteNumber = favouriteNumber;
        return this;
    }
}
