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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import models.Customer;
import models.Toolkit;
import views.main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MenuController implements Initializable {
    
    
    @FXML
    private Button dashboardButton;

    @FXML
    private Button foodButton;

    @FXML
    private Button restoButton;

    @FXML
    private Button cartButton;

    @FXML
    private Button notificationButton;

    @FXML
    private Button favoritesButton;
       
    @FXML
    private Button profileButton;
    
    @FXML
    private Circle imageProfile;

    @FXML
    private Label name;

    @FXML
    private AnchorPane loadingMenuViews;
    
    @FXML
    private AnchorPane contentPane;
    
    static String buttonName;
    
    void initializeName(Customer customer){
        String myName = (customer.getFirstName() + " " + customer.getLastName()).toUpperCase();
        name.setText(myName);
    }
    
    void getContentPane(){
        Toolkit.setMenuContentPane(loadingMenuViews);
    }
    
    void setContentPane(Pane pane){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(pane);
    }

    @FXML
    void loadingDashboard(ActionEvent event) throws Exception {
        try{
            AnchorPane dashboardPane = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
            loadingMenuViews.getChildren().setAll(dashboardPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setButtonActive() {
        
    }
    
    
    @FXML
    void loadingFavorites(ActionEvent event) {
        try{
            buttonName = "Favorites";
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/type-of-food.fxml"));
            Parent root = loader.load();
            TypeFoodController controller = loader.getController();
            controller.setFoodTypeName("Favorites");
            loadingMenuViews.getChildren().setAll(root);
        } catch(Exception ex){
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingFoodAndDrinks(ActionEvent event) {
        try{
            AnchorPane foodPane = FXMLLoader.load(getClass().getResource("/views/foodAndDrinks.fxml"));
            loadingMenuViews.getChildren().setAll(foodPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingNotifications(ActionEvent event) {
        try{
            BorderPane notificationsPane = FXMLLoader.load(getClass().getResource("/views/notifications.fxml"));
            loadingMenuViews.getChildren().setAll(notificationsPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingProfile(ActionEvent event) {
        try{
            BorderPane profilePane = FXMLLoader.load(getClass().getResource("/views/profile.fxml"));
            loadingMenuViews.getChildren().setAll(profilePane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadingRestaurants(ActionEvent event) {
        try{
            ScrollPane restaurantsPane = FXMLLoader.load(getClass().getResource("/views/restaurants.fxml"));
            loadingMenuViews.getChildren().setAll(restaurantsPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Button btn = (Button)event.getSource();
        btn.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image(main.class.getResourceAsStream("/resources/media/images/favicon.png")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    @FXML
    void loadingShoppingCart(ActionEvent event) {
        try{
            AnchorPane cartPane = FXMLLoader.load(getClass().getResource("/views/shoppingCart.fxml"));
            loadingMenuViews.getChildren().setAll(cartPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseManager.queryShoppingCart();
        getContentPane();
        try{
            AnchorPane dashboardPane = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
            loadingMenuViews.getChildren().setAll(dashboardPane);
        } catch(Exception ex){
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
