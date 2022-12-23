/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static database.DatabaseManager.cartList;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import models.ShoppingCartItem;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShoppingCartController implements Initializable {
    
    @FXML
    private AnchorPane shoppingPane;

    @FXML
    private Label totalAmount;

    @FXML
    private VBox tableView;

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
    
    @FXML
    private VBox displayPane;
    
    static ObservableList<ShoppingCartItem> tableList = FXCollections.observableArrayList();
    static double totalAmt;
    

    @FXML
    void decreaseQty(ActionEvent event) throws IOException {
        Integer quantity = Integer.parseInt(foodQuantity.getText());
        quantity--;
        foodQuantity.setText(""+quantity);
        update();
    }

    @FXML
    void increaseQty(ActionEvent event) throws IOException {
        Integer quantity = Integer.parseInt(foodQuantity.getText());
        quantity++;
        foodQuantity.setText(""+quantity);
        update();
    }
    
    void update() throws IOException{
        ShoppingCartItem item = cartList.get(getIndex());
        System.out.println(getIndex());
        double newTotal = Integer.parseInt(foodQuantity.getText()) * Double.parseDouble(foodPrice.getText());
        total.setText(""+newTotal);
        
        if(database.DatabaseManager.updateQuantity(foodQuantity, newTotal, item.getCartId())){
            tableView.getChildren().clear();
            initializeCart();
        }
        //displayPane.setVisible(false);
        
    }

    @FXML
    void pay(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/payment-method.fxml"));
            shoppingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    void removeFood(ActionEvent event) throws IOException {
        int index = getIndex();
        System.out.println(index);
        ShoppingCartItem item = cartList.get(index);
        
        boolean success = database.DatabaseManager.deleteFood(item.getCartId());
        if(success){
            cartList.clear();
            tableView.getChildren().clear();
            initializeCart();
            displayPane.setVisible(false);
        }
    }
    
    int getIndex(){
        for (int i=0; i<cartList.size(); i++){
            if(cartList.get(i).getFood().getName().equals(foodName.getText())){
                return i;
            }
        }
        return -1;
    }
    
    void initializeCart() throws IOException {
        //tableView.getChildren().clear();
        cartList.clear();
        database.DatabaseManager.queryShoppingCart();
        for(int i = 0; i<cartList.size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/table-row.fxml"));
            Parent root = loader.load();
            TableRowController controller = loader.getController();
            controller.setData(cartList.get(i));
            tableView.getChildren().add(root);
        }
        totalAmt = getTotalAmount();
        totalAmount.setText(""+totalAmt);
        
    }
      
    
    
    public static <E> ObservableList<E> removeDuplicates(ObservableList<E> list) {

        ObservableList<E> newList = FXCollections.observableArrayList();
        for (E aList : list) {
            if (!newList.contains(aList)) {
                newList.add(aList);
            }
        }
        return newList;
    }
    
    
    
    static Float getTotalAmount(){
        float totalAmount = 0;
        for (int i = 0; i<cartList.size(); i++){
            float amount = cartList.get(i).getQuantity() * cartList.get(i).getFood().getPrice();
            totalAmount += amount;
        }
        
        return totalAmount;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initializeCart();
        } catch (IOException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
