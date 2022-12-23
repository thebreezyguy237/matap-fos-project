/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static database.DatabaseManager.getRestaurant;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RestaurantsController implements Initializable {
    
    
    
    @FXML
    private HBox rowContainer;
    
    @FXML
    private ScrollPane restoPane;
    
    @FXML
    private VBox vboxContainer;

    
    public void loadButton(ActionEvent event) {
        try {
            FXMLLoader loader = FXMLLoader.load(getClass().getResource("/views/restaurantProfile.fxml"));
            Parent root = loader.load();
            RestaurantProfileController controller = loader.getController();
            controller.setData(getRestaurant());
            restoPane.getChildrenUnmodifiable().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void displayOrder(int index, HBox rowContainer){
        try {
            for (int i = index; i<database.DatabaseManager.buttonNameList.size(); i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/resto-thumb.fxml"));
                VBox vbox = fxmlLoader.load();
                RestoThumbController thumbController = fxmlLoader.getController();
                thumbController.setName(database.DatabaseManager.buttonNameList.get(i));
                    
                if (rowContainer.getChildren().size() == 3){
                    displayOrder(i, generateRowContainer());
                } else {
                    rowContainer.getChildren().add(vbox);
                }
                
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    HBox generateRowContainer(){
        HBox newRowContainer = new HBox();
        newRowContainer.setSpacing(40);
        newRowContainer.setAlignment(Pos.CENTER_LEFT);
        vboxContainer.getChildren().add(newRowContainer);
        return newRowContainer;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        database.DatabaseManager.buttonName();
        displayOrder(0, rowContainer);
    }    
    
}
