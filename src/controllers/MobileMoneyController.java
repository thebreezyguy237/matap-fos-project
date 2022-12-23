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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author OLIVIA
 */
public class MobileMoneyController implements Initializable {
    
    @FXML
    private ImageView logo;

    @FXML
    private Label title;

    @FXML
    private TextField textfield;

    @FXML
    private Button actionBtn;
    
    @FXML
    private Label message;

    @FXML
    void action(ActionEvent event) {
        if(actionBtn.getText().contains("Next")){
            
        }
    }
    
    void setTitle(String title){
        this.title.setText(title);
    }
    
    void setMessage(String message){
        this.message.setText(message);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
