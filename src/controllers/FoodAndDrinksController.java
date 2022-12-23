/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FoodAndDrinksController implements Initializable {
    
    @FXML
    private AnchorPane typeFoodPane;


    void loadFoodType(String foodType) {
        
        if(database.DatabaseManager.getFoodTypeID(foodType)){
            try {
            ScrollPane pane = FXMLLoader.load(getClass().getResource("/views/type-of-food.fxml"));
            typeFoodPane.getChildren().add(pane);
            
            } catch (IOException ex) {
                Logger.getLogger(FoodAndDrinksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    void loadAfricanMeals(MouseEvent event) {
        loadFoodType("African Food");
    }

    @FXML
    void loadAmericanMeals(MouseEvent event) {
        loadFoodType("American Food");
    }

    @FXML
    void loadAsianMeals(MouseEvent event) {
        loadFoodType("Asian Food");
    }

    @FXML
    void loadDrinks(MouseEvent event) {
        loadFoodType("Drinks");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
