package com.softStore.softStore.Class;

public class User {
    public String email;
    public String passwoard;
    public String country;
    public String city;
    public String zipCode;
    public String street;
    public String houseNumber;
    public String additionalDescription;

    public User(String email, String passwoard) {
        this.email = email;
        this.passwoard = passwoard;
    }

    public User(String email, String passwoard, String country, String city, String zipCode, String street, String houseNumber, String additionalDescription) {
        this.email = email;
        this.passwoard = passwoard;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.additionalDescription = additionalDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswoard() {
        return passwoard;
    }

    public void setPasswoard(String passwoard) {
        this.passwoard = passwoard;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }
}
