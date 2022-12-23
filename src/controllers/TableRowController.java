/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.ShoppingCartItem;

/**
 * FXML Controller class
 *
 * @author OLIVIA
 */
public class TableRowController implements Initializable {
    
    @FXML
    private HBox rowContainer;

    @FXML
    private Label mealName;

    @FXML
    private Label quantity;

    @FXML
    private Label price;

    @FXML
    private Label total;
    
    static TextField foodQuantity;
    static int cartId;
    
    public void setData(ShoppingCartItem item){
        cartId = item.getCartId();
        double newTotal = item.getQuantity() * item.getFood().getPrice();
        this.mealName.setText(item.getFood().getName());
        this.quantity.setText(""+item.getQuantity());
        this.price.setText(""+item.getFood().getPrice());
        this.total.setText(""+ newTotal);
    }
    
    @FXML
    void displayItem(MouseEvent event) {
        HBox hbox = (HBox) event.getSource();
        AnchorPane pane = (AnchorPane)hbox.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        VBox vbox = (VBox) pane.getChildren().get(4);
        vbox.setVisible(true);
        HBox hbox1 = (HBox) vbox.getChildren().get(1);
        Label foodName = (Label)hbox1.getChildren().get(0);
        foodName.setText(mealName.getText());
        HBox hbox2 = (HBox) vbox.getChildren().get(2);
        Label foodPrice = (Label)hbox2.getChildren().get(0);
        foodPrice.setText(price.getText());
        HBox hbox3 = (HBox) vbox.getChildren().get(3);
        Label foodTotal = (Label)hbox3.getChildren().get(0);
        foodTotal.setText(total.getText());
        HBox hbox4 = (HBox) vbox.getChildren().get(4);
        foodQuantity = (TextField)hbox4.getChildren().get(2);
        foodQuantity.setText(""+quantity.getText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
