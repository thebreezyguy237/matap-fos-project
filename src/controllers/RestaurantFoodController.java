/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Food;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RestaurantFoodController implements Initializable {
    
    @FXML
    private TextField searchBar;

    @FXML
    private Label foodType;

    @FXML
    private GridPane foodGridPane;
    
    

    @FXML
    void loadFoodAndDrinks(ActionEvent event) {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //database.DatabaseManager.foods();
        
        int column = 0;
        int row = 0;
        
        /*try {
            for (int i = 0; i < database.DatabaseManager.Restlist.size(); i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/food-thumb.fxml"));
                BorderPane pane = fxmlLoader.load();
                FoodThumbController thumbController = fxmlLoader.getController();
                thumbController.setData(database.DatabaseManager.list.get(i));
                    
                if (column == 2){
                    column = 0;
                    ++row;
                }
                
                foodGridPane.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(10));
                column++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }  
       
    
}
