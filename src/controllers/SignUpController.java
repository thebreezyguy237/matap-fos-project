/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Customer;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpController implements Initializable {
    
    @FXML
    private AnchorPane signUpPane;
    
    @FXML
    private AnchorPane loginBlock;
      
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private VBox vbox;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField verifyPassword;

    @FXML
    private JFXDatePicker dateOfBirthTextField;
    
    @FXML
    private TextField placeOfBirthTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private ComboBox<String> genderField;

    @FXML
    private CheckBox checkbox;
     
    @FXML
    private Button submit_btn;
    
    @FXML
    void submit(ActionEvent event) throws IOException {
       
            boolean success = database.DatabaseManager.signUp(checkbox, genderField, dateOfBirthTextField, passwordTextField,
                    verifyPassword, emailTextField, phoneNumberTextField, firstNameTextField, lastNameTextField,
                    userNameTextField, passwordTextField, addressTextField);
            
            if(success){
                JOptionPane.showMessageDialog(null, "Your account has been created successfully!");
                                
                submit_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
         
    }
   
  
    @FXML
    void login(ActionEvent event) {
        try{  
            AnchorPane loginPane = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            signUpPane.getChildren().setAll(loginPane);
        } catch(Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] gender = {"M", "F"};
        ObservableList<String> items = FXCollections.observableArrayList(gender);
        genderField.getItems().addAll(items);
        genderField.setValue(gender[0]);
    }    
    
}
