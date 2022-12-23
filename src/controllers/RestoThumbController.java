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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author OLIVIA
 */
public class RestoThumbController implements Initializable {
    
    
    @FXML
    private ImageView restoLogo;

    @FXML
    private Label restoName;
    
    @FXML
    private VBox boxContainer;
    
    public void setName(String restoName){
        this.restoName.setText(restoName);
    }
    
    public String getName(){
        return restoName.getText();
    }

    @FXML
    void loadResto(MouseEvent event) throws IOException {
        database.DatabaseManager.restoName = getName();
        
        VBox vbox = (VBox) event.getSource();
        AnchorPane pane = (AnchorPane) vbox.getParent().getParent().getParent();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/restaurantProfile.fxml"));
        AnchorPane root = loader.load();
        RestaurantProfileController controller = loader.getController();
        controller.setData(getRestaurant());
        
        pane.getChildren().setAll(root);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
