/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.DatabaseManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Food;
import models.Toolkit;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TypeFoodController implements Initializable {
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private Button backBtn;

    @FXML
    private Label foodType;

    @FXML
    private HBox rowContainer;
    
    @FXML
    private VBox vboxContainer;
    

    @FXML
    void goBack(ActionEvent event) throws IOException {
        if(MenuController.buttonName == null || MenuController.buttonName.contains("food")){
            Parent root = FXMLLoader.load(getClass().getResource("/views/foodAndDrinks.fxml"));
            Toolkit.menuContentPane.getChildren().setAll(root);
        } else if (MenuController.buttonName.contains("Favorites")){
            backBtn.setVisible(false);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/restaurantProfile.fxml"));
            Parent root = loader.load();
            RestaurantProfileController controller = loader.getController();
            controller.setData(DatabaseManager.getRestaurant());
            Toolkit.menuContentPane.getChildren().setAll(root);
        }
    }
    
    
    
    /*void getFoods(){
        String sql = "Select * from food_item";
        try{
            pst = LoginController.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int status = getLikeStatus(rs.getInt(0));
                Food food = new Food(rs.getInt(0), rs.getFloat(5), rs.getString(1), rs.getString(4), rs.getInt(6), FoodAndDrinksController.type_id);
                food.setLikeStatus(status);
                foodList.add(food);
            }
        } catch(Exception e){
            System.out.println(e);
        }
        System.out.println(foodList.size() + " - " + foodList);
    }*/
    
    
    void setFoodTypeName(String typeName){
        foodType.setText(typeName);
    }
    
    
    void displayOrder(ArrayList<Food> list, int index, HBox rowContainer){
        try {
            for (int i = index; i<list.size(); i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/food-thumb.fxml"));
                BorderPane pane = fxmlLoader.load();
                FoodThumbController thumbController = fxmlLoader.getController();
                thumbController.setData(list.get(i));
                thumbController.setLike(list.get(i).getLikeStatus() == 0 ? "Like" : "Unlike");
                    
                if (rowContainer.getChildren().size() == 3){
                    displayOrder(list, i, generateRowContainer());
                } else {
                    rowContainer.getChildren().add(pane);
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    HBox generateRowContainer(){
        HBox newRowContainer = new HBox();
        newRowContainer.setSpacing(40);
        newRowContainer.setAlignment(Pos.CENTER_LEFT);
        newRowContainer.setPadding(new Insets(20, 0, 0, 20));
        vboxContainer.getChildren().add(newRowContainer);
        return newRowContainer;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///getFoods();
        
        if(MenuController.buttonName == null || MenuController.buttonName.contains("food")){
            backBtn.setVisible(true);
            database.DatabaseManager.foods(database.DatabaseManager.typeId);
            displayOrder(database.DatabaseManager.foodList, 0, rowContainer);
        } else if(MenuController.buttonName.contains("Favorites")){
            database.DatabaseManager.favoritesFood();
            backBtn.setVisible(false);
            displayOrder(database.DatabaseManager.favoriteList, 0, rowContainer);
        } else {
            backBtn.setVisible(true);
            database.DatabaseManager.restoFoods(database.DatabaseManager.restoId);
            displayOrder(database.DatabaseManager.RestoFoodList, 0, rowContainer);
        }
    }  

    }    
    

