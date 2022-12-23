/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author OLIVIA
 */
public class Restaurant {
    private int id;
    private String name;
    private String slogan;
    private String username;
    private String password;
    private String email;
    private Date dateOfCreation;
    private String country;
    private String city;
    private String street;
    private String phoneNumber1;
    private String phoneNumber2;
    private Image profilePicture;

    public Restaurant() {
    }

    public Restaurant(int id, String name, String slogan, String username, String password, String email, Date dateOfCreation, String country, String city, String street, String phoneNumber1, String phoneNumber2, Image profilePicture) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfCreation = dateOfCreation;
        this.country = country;
        this.city = city;
        this.street = street;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }
    
    
}
