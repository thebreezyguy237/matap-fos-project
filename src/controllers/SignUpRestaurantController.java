/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpRestaurantController implements Initializable {
    
    
    @FXML
    private AnchorPane signUpResto;
    
    @FXML
    private BorderPane signUpPane;

    @FXML
    private TextField restaurantNameTextField;

    @FXML
    private TextField sloganTextField;

    @FXML
    private VBox vbox;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField verifyPasswordTextField;

    @FXML
    private JFXDatePicker dateOfCreationTextField;
    
     @FXML
    private TextField countryTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField phone1TextField;

    @FXML
    private TextField phone2TextField;
    
    @FXML
    private CheckBox checkbox;
    
    @FXML
    private TextField emailTextField;
    
    @FXML
    void submit(ActionEvent event) throws IOException {
        
        boolean success = database.DatabaseManager.signUpRestaurant(checkbox, dateOfCreationTextField, passwordTextField,
                verifyPasswordTextField, restaurantNameTextField, emailTextField, userNameTextField,
                phone1TextField, countryTextField, streetTextField, cityTextField, sloganTextField, 
                phone2TextField);
        if (success){
            JOptionPane.showMessageDialog(null, "Your account has been created successfully!");
                        
            Button btn = (Button)event.getSource();
            btn.getScene().getWindow().hide();
                        
            Parent root = FXMLLoader.load(getClass().getResource("/views/restaurants/menuRestaurants.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/resto/favicon.png")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login Page");
            //stage.setResizable(false);
            stage.show();
                        
                        
            Locale.setDefault(Locale.ENGLISH);   
        }
        
    }
    
    
    @FXML
    void login(ActionEvent event) {
        try{  
            AnchorPane loginPane = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            signUpResto.getChildren().setAll(loginPane);
        } catch(Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
