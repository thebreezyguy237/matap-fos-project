/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.Restaurant;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RestaurantProfileController implements Initializable {
    
    @FXML
    private AnchorPane restoPane;

    @FXML
    private ImageView restoImg;

    @FXML
    private Label restoName;

    @FXML
    private Label restoSlogan;

    @FXML
    private Label restoCountry;

    @FXML
    private Label restoCity;

    @FXML
    private Label restoStreet;

    @FXML
    private Label restoPhone;
    
    int restoId;

    @FXML
    void loadRestoProducts(ActionEvent event) {
        try {
            database.DatabaseManager.restoId = restoId;
            MenuController.buttonName = "resto";
            ScrollPane pane = FXMLLoader.load(getClass().getResource("/views/type-of-food.fxml"));
            restoPane.getChildren().setAll(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void goBack(ActionEvent event) {
        try {
            ScrollPane pane = FXMLLoader.load(getClass().getResource("/views/restaurants.fxml"));
            restoPane.getChildren().setAll(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void setData(Restaurant resto){
        restoId = resto.getId();
        restoName.setText(resto.getName());
        restoSlogan.setText(resto.getSlogan());
        restoCountry.setText(resto.getCountry());
        restoCity.setText(resto.getCity());
        restoStreet.setText(resto.getStreet());
        restoPhone.setText(resto.getPhoneNumber2() == null ? resto.getPhoneNumber1() : resto.getPhoneNumber1() + "/" + resto.getPhoneNumber2());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
