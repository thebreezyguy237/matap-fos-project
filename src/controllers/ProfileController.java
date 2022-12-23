/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ProfileController implements Initializable {
    
    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Label dobTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField countryTxt;

    @FXML
    private Label genderTxt;

    @FXML
    private TextField phoneTxt;

    
    void initializeProfile(){
        
        usernameTxt.setText(database.DatabaseManager.customer.getUsername());
        nameTxt.setText(database.DatabaseManager.customer.getLastName() + " " + database.DatabaseManager.customer.getFirstName());
        emailTxt.setText(database.DatabaseManager.customer.getEmail());
        passwordTxt.setText(database.DatabaseManager.customer.getPassword());
        addressTxt.setText(database.DatabaseManager.customer.getAddress());
        dobTxt.setText(database.DatabaseManager.customer.getDateOfBirth().toString());
        //countryTxt.setText(database.DatabaseManager.customer.get());
        //genderTxt.getItems().add(rs.getString("customer_gender"));
        genderTxt.setText(database.DatabaseManager.customer.getGender());
        phoneTxt.setText(database.DatabaseManager.customer.getPhoneNumber()); 
              
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeProfile();
    }    
    
}
