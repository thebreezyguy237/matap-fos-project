/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author HP
 */
public class Food {
    private int id;
    private float price;
    private String name;
    private String description;  
    private String foodImage;
    private int likeStatus;
    private int foodStatus;
    private int foodTypeId;

    public Food() {
    }

    public Food(int id, float price, String name, String description, int foodStatus, int foodTypeId) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.foodStatus = foodStatus;
        this.foodTypeId = foodTypeId;
    }

    public Food(int id, float price, String name, String description, String foodImage, int likeStatus) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.foodImage = foodImage;
        this.likeStatus = likeStatus;
    }
    
    
    
    public Food(float price, String name, String description, String foodImage, int likeStatus) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.foodImage = foodImage;
        this.likeStatus = likeStatus;
    }

    public Food(float price, String name, String description, int likeStatus) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.likeStatus = likeStatus;
    }

    public Food(float price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public int getFoodStatus() {
        return foodStatus;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }
    
    

    public void setFoodStatus(int foodStatus) {
        this.foodStatus = foodStatus;
    }
    
    
    
    public int getLikeStatus() {
        return likeStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
    
    
}
