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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.Food;
import models.Order;
import models.ShoppingCartItem;
import models.Toolkit;

/**
 * FXML Controller class
 *
 * @author OLIVIA
 */
public class SetOrderItemController implements Initializable {
    
    @FXML
    private VBox displayPane;

    @FXML
    private ImageView foodImage;

    @FXML
    private Label foodName;

    @FXML
    private Label foodPrice;

    @FXML
    private Label total;

    @FXML
    private TextField foodQuantity;
    
    public static Order order =  new Order();;
    public static ShoppingCartItem item = new ShoppingCartItem();
    public static Food newFood;


    @FXML
    void decreaseQty(ActionEvent event) {
        Integer quantity = Integer.parseInt(foodQuantity.getText());
        if(quantity > 0){
            quantity--;
        } else {
            quantity = 0;
        }
        foodQuantity.setText(""+quantity);
        double newTotal = Integer.parseInt(foodQuantity.getText()) * Double.parseDouble(foodPrice.getText());
        total.setText(""+newTotal);
    }

    @FXML
    void increaseQty(ActionEvent event) {
        Integer quantity = Integer.parseInt(foodQuantity.getText());
        quantity++;
        foodQuantity.setText(""+quantity);
        double newTotal = Integer.parseInt(foodQuantity.getText()) * Double.parseDouble(foodPrice.getText());
        total.setText(""+newTotal);
    }

    @FXML
    void pay(ActionEvent event) {
        Button btn = (Button) event.getSource();
        btn.getScene().getWindow().hide();
        
        order.setCustomer(database.DatabaseManager.customer);
        order.setOrderDate(new java.sql.Date(new java.util.Date().getTime()));
        order.setStatus(0);
        order.setTotalAmount(Integer.parseInt(foodQuantity.getText()) * newFood.getPrice());
        
        item.setOrder(order);
        item.setFood(newFood);
        item.setQuantity(Integer.parseInt(foodQuantity.getText()));
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/payment-method.fxml"));
            Toolkit.menuContentPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(SetOrderItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void setData(Food food){
        newFood = food;
        foodName.setText(food.getName());
        foodPrice.setText(""+ food.getPrice());
        total.setText(""+food.getPrice());
        foodQuantity.setText("1");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
