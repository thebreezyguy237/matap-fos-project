/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.DatabaseManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Food;
import models.Order;
import models.ShoppingCartItem;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FoodThumbController implements Initializable {
    
    @FXML
    private ImageView foodImage;
    
    @FXML
    private Label foodName;

    @FXML
    private Label foodDescription;

    @FXML
    private Label foodPrice;
    
    @FXML
    private Button payBtn;
    
    @FXML
    private Button likeBtn;
    
    int foodId;
    
    public static Order order;
    

    @FXML
    void addToCart(ActionEvent event) {
        order = database.DatabaseManager.getOrderPending();
        ShoppingCartItem item = new ShoppingCartItem();
        item.setOrder(order);
        item.setFood(database.DatabaseManager.getFood(foodId));
        item.setQuantity(1);
        if(alreadyAdded(item)){
            JOptionPane.showMessageDialog(null, "This product has already been added to the cart :)");
        } else {
           boolean success = database.DatabaseManager.addToCart(item);
           if(success)
                JOptionPane.showMessageDialog(null, "Your product has been added to the cart :)"); 
        }
            
        
    }
    
    boolean alreadyAdded(ShoppingCartItem item){
        for(int i = 0; i<DatabaseManager.cartList.size(); i++){
            if(item.getFood().getId() == DatabaseManager.cartList.get(i).getFood().getId()){
                return true;
            }
        }
        return false;
    }
    
   /* int getOrderId(){
        
    }*/

    @FXML
    void addToFavorite(ActionEvent event) {
       if (likeBtn.getText().contains("Like")){
            likeBtn.setText("Unlike");
            database.DatabaseManager.like(foodId);
        } else {
           likeBtn.setText("Like");
           database.DatabaseManager.unlike(foodId);
       }
    }
    
    void setLike(String likeUnlike){
        likeBtn.setText(likeUnlike);
    }

    @FXML
    void pay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/set-order-item.fxml"));
        Parent root = loader.load();
        SetOrderItemController controller = loader.getController();
        controller.setData(database.DatabaseManager.getFood(foodId));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Create Your Order");
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
       /* Order order = new Order();
        order.setCustomer(database.DatabaseManager.customer);
        order.setId(database.DatabaseManager.generateOrderId());
        order.setOrderDate(new java.sql.Date(new java.util.Date().getTime()));
        order.setTotalAmount(foodId);
        ShoppingCartController.totalAmt = Double.parseDouble(foodPrice.getText());
        ShoppingCartItem item = new ShoppingCartItem();
        item.setItemName(); */
        
        //item.setCartId();
        /*
        String sql = "Insert into order_details(food_id, amount, no_of_serving, total_amount) values (?,?,?,?)";
        try{
            pst = LoginController.conn.prepareStatement(sql);
            pst.setInt(1, foodId());
            pst.setFloat(2, (float) Double.parseDouble(foodPrice.getText()));
            pst.setInt(3, 1);
            pst.setFloat(4, (float) (Double.parseDouble(foodPrice.getText()) * 1));
            boolean insert = pst.execute();
            
            if(insert){
                int orderDetailsId = rs.getInt("order_details_id");
                //int orderId = getOrderId();
                String sql2 = "Insert into orders(customer_id, order_date, total_amount, order_status) values (?,?,?,?)";
                
                pst = LoginController.conn.prepareStatement(sql2);
                pst.setInt(1, Integer.parseInt(LoginController.id));
                pst.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
                pst.setFloat(3, (float)(Double.parseDouble(foodPrice.getText()) * 1));
                pst.setInt(4, 1); //pending
                insert = pst.execute();
                
                if(insert){
                   AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/payment-method.fxml"));
                   payBtn.getScene().setRoot(pane);
                }
                //rs = pst.executeUpdate();
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        */
    }
    
    /**
     *
     * @param food
     */
    public void setData(Food food){
        foodId = food.getId();
        foodImage.setImage(new Image(getClass().getResourceAsStream("/resources/media/images/logo.png")));
        foodName.setText(food.getName());
        foodDescription.setText(food.getDescription());
        foodPrice.setText(""+food.getPrice());
    }
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
