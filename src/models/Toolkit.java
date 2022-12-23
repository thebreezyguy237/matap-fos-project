/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author OLIVIA
 */
public class Toolkit {

    public static AnchorPane menuContentPane;
    
    public static boolean isValidEmail(TextField email){
        return email.getText().contains("@") & (email.getText().contains(".com") || email.getText().contains(".org"));
    }
    
    
    public static boolean isValidPhoneNumber(TextField phoneNumber){
        return phoneNumber.getText().substring(4).length() == 9 && phoneNumber.getText().substring(4).startsWith("6");   
    }
    
    public static void setMenuContentPane(AnchorPane pane){
        menuContentPane = pane;
    }
}
