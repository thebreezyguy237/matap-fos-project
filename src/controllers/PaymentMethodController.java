/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PaymentMethodController implements Initializable {
    
    @FXML
    private AnchorPane paymentMethodPane;

    @FXML
    void MTNPaymentMethod(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mobile-money.fxml"));
            Parent root = loader.load();
            MobileMoneyController controller = loader.getController();
            controller.setTitle("MTN Money Payment");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Payement");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PaymentMethodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void OMPaymentMethod(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mobile-money.fxml"));
            Parent root = loader.load();
            MobileMoneyController controller = loader.getController();
            controller.setTitle("Orange Money Payment");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Payement");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PaymentMethodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void mastercardPaymentMethod(MouseEvent event) {

    }

    @FXML
    void saaraPaymentMethod(MouseEvent event) {

    }

    @FXML
    void visaPaymentMethod(MouseEvent event) {

    }

    @FXML
    void yupPaymentMethod(MouseEvent event) {

    }
    
    @FXML
    void goBack(ActionEvent event) {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
