/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.jfoenix.controls.JFXDatePicker;
import static controllers.DashboardController.countFrequencies;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import models.Customer;
import models.Food;
import models.Order;
import models.Restaurant;
import models.ShoppingCartItem;
import models.Toolkit;

/**
 *
 * @author OLIVIA
 */
public class DatabaseManager {
    static Connection conn;
    static PreparedStatement pst;
    static ResultSet rs;
    public static Customer customer = new Customer();
    public static Restaurant resto;
    public static int typeId;
    public static int restoId;
    public static int orderId;
    public static String restoName;
    
    static ArrayList<Integer> orderIds = new ArrayList<>();
    static ArrayList<Integer> foodIds = new ArrayList<>();
    public static ArrayList<Food> foodList = new ArrayList<>(); 
    public static ArrayList<Food> RestoFoodList = new ArrayList<>(); 
    public static ArrayList<String> buttonNameList = new ArrayList<>();
    public static ArrayList<Food> favoriteList = new ArrayList<>(); 
    public static ArrayList<ShoppingCartItem> cartList = new ArrayList<>(); 
    
    public static void connectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/matap-project", "root", "");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public static boolean loginCustomer(TextField username, TextField password){
        String sql = "Select * from customers where customer_username = ? and customer_password = ?";
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                rs = pst.executeQuery();
         
                if(rs.next()){
                    customer.setId(rs.getInt("customer_id"));
                    customer.setAddress(rs.getString("customer_address"));
                    customer.setUsername(rs.getString("customer_username"));
                    customer.setFirstName(rs.getString("customer_firstName"));
                    customer.setLastName(rs.getString("customer_lastName"));
                    customer.setDateOfBirth(rs.getDate("customer_dateOfBirth"));
                    customer.setEmail(rs.getString("customer_email"));
                    customer.setGender(rs.getString("customer_gender"));
                    customer.setPassword(rs.getString("customer_password"));
                    customer.setPhoneNumber(rs.getString("customer_phoneNumber"));
                    
                    return true;
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    username.clear();
                    password.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
    }
    
    public static boolean loginRestaurantMenu(TextField username, TextField password){
        String sql = "Select * from restaurants where resto_username = ? and resto_password = ?"; 
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                rs = pst.executeQuery();
                if(rs.next()){
                    resto.setCity(rs.getString("resto_city"));
                    resto.setCountry(rs.getString("resto_country"));
                    resto.setDateOfCreation(rs.getDate("resto_dateOfCreation"));
                    resto.setEmail(rs.getString("resto_email"));
                    resto.setId(rs.getInt("resto_id"));
                    resto.setName(rs.getString("resto_name"));
                    resto.setPassword(rs.getString("resto_password"));
                    resto.setPhoneNumber1(rs.getString("resto_phoneNumber1"));
                    resto.setPhoneNumber2(rs.getString("resto_phoneNumber2"));
                    resto.setSlogan(rs.getString("resto_slogan"));
                    resto.setStreet(rs.getString("resto_street"));
                    
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    username.clear();
                    password.clear();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            return false;
    }
    
    
    public static float totalSpend(){
        float totalAmount = 0;
        float amount;
        String sql = "select amount from payment where customer_id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, customer.getId());
            rs = pst.executeQuery();
            
            while(rs.next()){
                amount = rs.getFloat(1);
                totalAmount += amount;
            }
            
            return totalAmount;
            
        } catch(Exception ex){
            System.out.println(ex);
        }
        
        return 0;
    }
    
    
    public static int getNumberOfOrdersPerMonth(){
        int size;
        String sql = "Select * from orders where customer_id = ? and order_date = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, customer.getId());
            pst.setDate(2, (Date) new java.util.Date());
            rs = pst.executeQuery();
            
            if(rs.last()){ 
                size = rs.getRow();  
                return size;
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    
    //This method returns the number of orders made by the user.
    public static int getNumberOfOrders(){
        int size;
        String sql = "Select * from orders where customer_id = ? and order_status = 1";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, customer.getId());
            rs = pst.executeQuery();
            
            if(rs.last()){ //if rs.last() exists, the method getRow() returns the current row number of the last row which is equivalent to the number of orders.
                size = rs.getRow();  
                return size;
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static String favoriteFood(){
        
        String food = null;
        int i = 0;
        String sql1 = "Select order_id from orders where customer_id = ?";
        try {
            pst = conn.prepareStatement(sql1);
            pst.setInt(1, customer.getId());
            rs = pst.executeQuery();
            
            while(rs.next()){
                orderIds.add(rs.getInt(1));
            }
            
            
            String sql2 = "Select food_id from order_details where order_id = ?";
            pst = conn.prepareStatement(sql2);
            while(i < orderIds.size()){
                 pst.setString(1, ""+orderIds.get(i));
                 rs = pst.executeQuery();
                 
                 if(rs.next()){
                    foodIds.add(Integer.parseInt(rs.getString(1)));
                 }
                 
                 i++;
            }
            
            int favoritefoodID = countFrequencies(foodIds);
            
            String sql3 = "Select food_name from food_item where food_id = ?";
            pst = conn.prepareStatement(sql3);
            rs = pst.executeQuery();
            if(rs.next()){
                food = rs.getString(1);
            }
            
             if (food == null){
                 food = "No Favorite Meal Yet!";
             }
             
             return food;
        } catch (Exception ex){
            System.out.println(ex);
        }
        
        return null;
    }
    
    
   public static boolean signUp(CheckBox checkbox, ComboBox<String> gender, JFXDatePicker dateOfBirth, TextField... textfields){
       
            String sql = "insert into customers (customer_firstName, customer_lastName, customer_email, customer_userName, customer_password, "
            + "customer_dateOfBirth, customer_address, customer_phoneNumber, customer_gender, customer_profilePicture) values (?,?,?,?,?,?,?,?,?,?)";
            try{
            
            if (checkbox.isSelected()){
                if (textfields[0].getText().equals(textfields[1].getText())) {
                    if (Toolkit.isValidEmail(textfields[2])){
                        if (Toolkit.isValidPhoneNumber(textfields[3])){
                            if (textfields[4].getText().equals("") || textfields[5].getText().equals("") ||
                                    textfields[2].getText().equals("") || textfields[6].getText().equals("") ||
                                    textfields[0].getText().equals("") || dateOfBirth.getValue().toString().equals("") ||
                                    textfields[7].getText().equals("") || textfields[3].getText().equals("") ||
                                    gender.getValue().equals("")
                                    ) {
                                        JOptionPane.showMessageDialog(null, "Some Fields are empty!");
                            } else {
                                long dOB = dateOfBirth.getValue().toEpochDay(); 
                                long milliseconds = dOB * 24 * 60 * 60 * 1000;
                                java.sql.Date date = new java.sql.Date(milliseconds);

                                pst = conn.prepareStatement(sql);
                                pst.setString(1, textfields[4].getText());
                                pst.setString(2, textfields[5].getText());
                                pst.setString(3, textfields[2].getText());
                                pst.setString(4, textfields[6].getText());
                                pst.setString(5, textfields[0].getText());
                                pst.setDate(6, date);
                                pst.setString(7, textfields[7].getText());
                                pst.setString(8, textfields[3].getText());
                                pst.setString(9, gender.getValue());
                                pst.setString(10, "NULL");

                                return pst.execute();
                                
                            }
                        } else 
                            JOptionPane.showMessageDialog(null, "Phone number is not valid!");
                    } else 
                        JOptionPane.showMessageDialog(null, "Email is not valid!");
                } else
                    JOptionPane.showMessageDialog(null, "Your passwords are not matching!");
            } else {
                JOptionPane.showMessageDialog(null, "You didnot read the terms and contracts!");
            }
            
            } catch(Exception e) {
                System.out.println(e);
            } 
            return false;
   }
   
   public static boolean signUpRestaurant(CheckBox checkbox, JFXDatePicker dateOfCreation, TextField... textfields){
       
            String sql = "insert into restaurants (resto_name, resto_slogan, resto_email, resto_username, resto_password, "
            + "resto_dateOfCreation, resto_country, resto_city, resto_street, resto_phoneNumber1, resto_phoneNumber2, resto_profilePicture)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
            try{
            
            if (checkbox.isSelected()){
                if (textfields[0].getText().equals(textfields[1].getText())) {
                    if (textfields[2].getText().equals("") ||
                            textfields[3].getText().equals("") || textfields[4].getText().equals("") ||
                            textfields[0].getText().equals("") || dateOfCreation.getValue().toString().equals("") ||
                            textfields[5].getText().equals("") || 
                            textfields[6].getText().equals("") || textfields[7].getText().equals("") ||
                            textfields[8].getText().equals("")
                            ) {
                        JOptionPane.showMessageDialog(null, "Some Fields are empty!");
                        
                    } else {
                        long dOB = dateOfCreation.getValue().toEpochDay(); 
                        long milliseconds = dOB * 24 * 60 * 60 * 1000;
                        java.sql.Date date = new java.sql.Date(milliseconds);

                        pst = conn.prepareStatement(sql);
                        pst.setString(1, textfields[2].getText());
                        pst.setString(2, textfields[9].getText());
                        pst.setString(3, textfields[3].getText());
                        pst.setString(4, textfields[4].getText());
                        pst.setString(5, textfields[0].getText());
                        pst.setDate(6, date);
                        pst.setString(7, textfields[6].getText());
                        pst.setString(8, textfields[8].getText());
                        pst.setString(9, textfields[7].getText());
                        pst.setString(10, textfields[5].getText());
                        pst.setString(11, textfields[10].getText());
                        pst.setString(12, "NULL");
                        return pst.execute();

                    } 
                    
                } else
                    JOptionPane.showMessageDialog(null, "Your passwords are not matching!");
            
            } else {
            JOptionPane.showMessageDialog(null, "You didnot read the terms and contracts!");
            }
            
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            return false;
   }
   
   
   public static boolean getFoodTypeID(String foodType){
       String sql = "Select food_type_id from food_type where type_name=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, foodType);
            rs = pst.executeQuery();
            if (rs.next()){
                typeId = rs.getInt("food_type_id");
                System.out.println(typeId);
                return true;
           }
        }catch (SQLException ex) {
            System.out.println("An error occured!");  
        }
        return false;
   }
   
   
   public static int foodId(){
        String sql = "Select food_id from food_item where food_type_id = ? and resto_id=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, typeId);
            pst.setInt(2, 1);
            rs = pst.executeQuery(); 
            
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch(Exception e){
            System.out.println("An error occured!");
        }
        return 0;
    }
   
   public static void like(int foodId){
       String sql = "Insert into favorites(food_id, status, date_recorded, customer_id) values (?,?,?,?)";
            try {
               pst = conn.prepareStatement(sql);
               pst.setInt(1, foodId);
               pst.setInt(2, 1);
               pst.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
               pst.setInt(4, customer.getId());
               pst.execute();
               
           } catch (Exception e) {
                System.out.println(e);
           }
   }
   
   public static void unlike(int foodId){
       String sql = "Delete from favorites where food_id = ? and customer_id = ?";
            try {
               pst = conn.prepareStatement(sql);
               pst.setInt(1, foodId);
               pst.setInt(2, customer.getId());
               pst.execute();
           } catch (Exception e) {
                System.out.println(e);
           }
   }
   
   public static void restoFoods(int restoId){
       RestoFoodList.clear();
        String sql = "Select * from food_item where resto_id=?";
        
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, restoId);
            rs = pst.executeQuery();
            
            while(rs.next()){
                RestoFoodList.add(new Food(rs.getFloat("food_price"), rs.getString("food_name"), rs.getString("food_ingredients")));
            }
        }catch(Exception e){
            System.out.println("An error has occured!");
        }
        
    }
   
   public static void buttonName(){
       buttonNameList.clear();
        String sql = "Select distinct resto_name from restaurants";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                buttonNameList.add(rs.getString(1));
                System.out.println(buttonNameList);
            }
        }catch (SQLException ex) {
              System.out.println("An error occured");
        }
        
    }
   
   public static boolean deleteFood(int cartId){
       String sql = "Delete from order_details where order_details_id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cartId);
            return pst.execute();
        } catch(Exception ex){
            System.out.println(ex);
        }
        return false;
   }
   
   public static String getFoodName(int foodId){
        String sql = "select food_name from food_item where food_id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, foodId);
            rs = pst.executeQuery();
                      
            if(rs.next()){
                return rs.getString(1);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
         return ""; 
    }
   
   
   public static boolean updateQuantity(TextField foodQuantity, double newTotal, int cartId){
        String sql = "Update order_details set no_of_serving = ?, total_amount = ? where order_details_id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(foodQuantity.getText()));
            pst.setDouble(2, newTotal);
            pst.setInt(3, cartId);
            return pst.execute();
            
        } catch(Exception e){
            System.out.println(e);
        }
       return false;
    }
   
   public static int getLikeStatus(int foodId){
        String sql = "select status from favorites where food_id = ? and customer_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, foodId);
            pst.setInt(2, customer.getId());
            ResultSet rs1 = pst.executeQuery();
            
            if(rs1.next()){
                return rs1.getInt("status");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
   
   
   
   public static void favoritesFood(){
       favoriteList.clear();
       String sql = "Select food_id from favorites where customer_id  = ?";
        
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, customer.getId());
            rs = pst.executeQuery();
            
            while(rs.next()){
                Food food = getFood(rs.getInt("food_id"));
                food.setLikeStatus(1);
                favoriteList.add(food);
            }
        }catch(Exception e){
            System.out.println("An error has occured!");
        }
   }
   
   
   public static Food getFood(int foodId){
        String sql = "Select * from food_item where food_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, foodId);
            rs = pst.executeQuery();
            
            if(rs.next()){
                return new Food(foodId, rs.getFloat("food_price"), rs.getString("food_name"), rs.getString("food_ingredients"), rs.getInt("food_status"), rs.getInt("food_type_id"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
   
   public static void foods(int foodTypeId){
       foodList.clear();
       String sql = "Select * from food_item where food_type_id=?";
        
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, foodTypeId);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Food food = new Food(rs.getInt("food_id"), rs.getFloat("food_price"), rs.getString("food_name"), rs.getString("food_ingredients"), rs.getInt("food_status"), rs.getInt("food_type_id"));
                food.setLikeStatus(getLikeStatus(food.getId()));
                foodList.add(food);
                System.out.println(foodList);
            }
        }catch(Exception e){
            System.out.println("An error has occured!");
        }
   }
   
   
   public static void queryShoppingCart(){
       String sql = "select order_id from orders where customer_id = ? and order_status = 0";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, customer.getId());
            rs = pst.executeQuery();
            
            if(rs.next()){
                orderId = rs.getInt(1);
                Order order = getOrder(orderId);
                String sql2 = "Select * from order_details where order_id = ?";
                PreparedStatement pst1 = conn.prepareStatement(sql2);
                pst1.setInt(1, orderId);
                ResultSet rs1 = pst1.executeQuery();
                
                while(rs1.next()){
                    int cartId = rs1.getInt("order_details_id");
                    int foodId = rs1.getInt("food_id");
                    Food food = getFood(foodId);
                    int numberOfServing = rs1.getInt(5);
                    float amount = rs1.getFloat("amount");
                    float totAmt = rs1.getFloat("total_amount");
                    ShoppingCartItem item = new ShoppingCartItem(cartId, order, food, numberOfServing);
                    cartList.add(item);
                }
                
            }
        
        } catch(Exception e){
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e);
        }
   }
   
   public static Restaurant getRestaurant(){
       String sql = "Select * from restaurants where resto_name = ?";
       try {
           pst = conn.prepareStatement(sql);
           pst.setString(1, restoName);
           rs = pst.executeQuery();
           
           if(rs.next()){
               Restaurant resto = new Restaurant();
               resto.setId(rs.getInt(1));
               resto.setCity(rs.getString(9));
               resto.setCountry(rs.getString(8));
               resto.setStreet(rs.getString(10));
               resto.setName(rs.getString(2));
               resto.setSlogan(rs.getString(3));
               resto.setPhoneNumber1(rs.getString(11));
               resto.setPhoneNumber2(rs.getString(12));
               return resto;
           }
       } catch (Exception e) {
           System.out.println(e);
       }
       return null;
   }
   
   
   public static Order getOrderPending(){
       String sql = "Select * from orders where order_status = 0 and customer_id = ?";
       try {
           pst = conn.prepareStatement(sql);
           pst.setInt(1, customer.getId());
           rs = pst.executeQuery();
           
           if(rs.next()){
               Order order = new Order();
               order.setCustomer(customer);
               order.setId(rs.getInt("order_id"));
               order.setOrderDate(rs.getDate("order_date"));
               order.setStatus(rs.getInt("order_status"));
               order.setTotalAmount(rs.getDouble("total_amount"));
               return order;
           } else {
               Order order = new Order();
               order.setCustomer(customer);
               order.setOrderDate(new java.sql.Date(new java.util.Date().getTime()));
               order.setStatus(0);
               order.setTotalAmount(0);
               return order;
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }
   
   public static Order getOrder(int orderId){
       String sql = "Select * from orders where order_id = ?";
       try {
           pst = conn.prepareStatement(sql);
           pst.setInt(1, orderId);
           rs = pst.executeQuery();
           
           if(rs.next()){
               Order order = new Order();
               order.setCustomer(customer);
               order.setId(rs.getInt("order_id"));
               order.setOrderDate(rs.getDate("order_date"));
               order.setStatus(rs.getInt("order_status"));
               order.setTotalAmount(rs.getDouble("total_amount"));
               return order;
           } 
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }
   
   public static boolean addToCart(ShoppingCartItem item){
       
       if(orderExist(item.getOrder())){
           insertIntoOrderDetails(item);
           return true;
       } else if(insertIntoOrder(item.getOrder())){
               insertIntoOrderDetails(item);
               return true;
       }
       
       return false;
   }
   
   public static boolean insertIntoOrderDetails(ShoppingCartItem item){
       String sql = "Insert into order_details(order_id, food_id, amount, no_of_serving, total_amount) values (?,?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setFloat(1, item.getOrder().getId());
            pst.setInt(2, item.getFood().getId());
            pst.setFloat(3, item.getFood().getPrice());
            pst.setInt(4, 1);
            pst.setFloat(5, item.getFood().getPrice() * item.getQuantity());
            return pst.execute(); 
           
        } catch(Exception e){
            System.out.println("An error occured!");
        }
        return false;
   }
   
   public static boolean insertIntoOrder(Order order){
       String sql = "Insert into orders(customer_id, order_date, total_amount, order_status) values (?,?,?,?)";
       try {
           pst = conn.prepareStatement(sql);
           pst.setInt(1, customer.getId());
           pst.setDate(2, order.getOrderDate());
           pst.setDouble(3, order.getTotalAmount());
           pst.setInt(4, order.getStatus());
           return pst.execute();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }
   
   public static boolean orderExist(Order order){
       String sql = "Select * from orders where order_id = ?";
       try {
           pst = conn.prepareStatement(sql);
           pst.setInt(1, order.getId());
           rs = pst.executeQuery();
           if(rs.next()){
               return true;
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }
   
}
