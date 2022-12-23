/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.dashboardThumb;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardController implements Initializable {

    
    @FXML
    private Label username;
    
    @FXML
    private HBox statHbox;
    
    
    private List<dashboardThumb> thumbs = new ArrayList<>();
    
     
    //This method initialises a dashboard thumb based on orders and returns it.
    private dashboardThumb orderThumb(){
        
        int numberOfOrders = database.DatabaseManager.getNumberOfOrders();
        
        dashboardThumb thumb = new dashboardThumb();
        thumb.setThumbImgSrc("/resources/media/images/orders-icon.png");
        thumb.setThumbButtonText("Order Now!");
        thumb.setThumbQuestion("Do you want to order more?");
        
        if (numberOfOrders > 1)
            thumb.setThumbText("You made " +numberOfOrders+ " orders inside our platform");
        else
            thumb.setThumbText("You made " +numberOfOrders+ " order inside our platform");
        
        thumb.setThumbStat(numberOfOrders);
        thumbs.add(thumb);
        
        return thumb;
    }
    
    private dashboardThumb favoriteFoodThumb(){
        
        String favoriteFood = database.DatabaseManager.favoriteFood();
        
        dashboardThumb thumb = new dashboardThumb();
        thumb.setThumbImgSrc("/resources/media/images/orders-icon.png");
        thumb.setThumbButtonText("Order Now!");
        thumb.setThumbQuestion("Do you want to order more?");
       
        thumb.setThumbText("Your favorite food is made " +favoriteFood+ " order inside our platform");
        
        thumb.setThumbStat(Integer.parseInt(favoriteFood));
        thumbs.add(thumb);
        
        return thumb;
    }
    
    private dashboardThumb totalSpendThumb(){
        
        float totalSpend = database.DatabaseManager.totalSpend();
        
        dashboardThumb thumb = new dashboardThumb();
        thumb.setThumbImgSrc("/resources/media/images/orders-icon.png");
        thumb.setThumbButtonText("Order Now!");
        thumb.setThumbQuestion("Do you want to order more?");
       
        thumb.setThumbText("You spend " +totalSpend+ "FCFA inside our platform");
        
        thumb.setThumbStat((int) totalSpend);
        thumbs.add(thumb);
        
        return thumb;
    }
    
    
    
    public static <T> T countFrequencies(ArrayList<T> list) {
        Map<T, Integer> map = new HashMap<>();
        
        for (T t : list){
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }
        
        Entry<T, Integer> max = null;
        
        for (Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        
        return max.getKey();
    }
    
    /*private void initializeOrderDashboardThumb(int row, int column) throws IOException{    
            VBox thumb = FXMLLoader.load(getClass().getResource("/views/dashboard-thumb.fxml"));
            dashboardGrid.add(thumb, row, column);
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        username.setText(database.DatabaseManager.customer.getUsername());
        orderThumb();
        totalSpendThumb();
        
        try {
            for (int i = 0; i<thumbs.size(); i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/dashboard-thumb.fxml"));
                VBox pane = fxmlLoader.load();
                DashboardThumbController thumbController = fxmlLoader.getController();
                thumbController.setData(thumbs.get(i));
                
                statHbox.getChildren().add(pane);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
        
        
