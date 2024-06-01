package org.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.lang.reflect.Method;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    public WebView webView;
    @FXML
    public WebEngine webEngine;

    @FXML
    private Button car_rent;

    @FXML
    private Button dashboard_btn;

    @FXML
    private ImageView dashboard_img;

    @FXML
    private Pane dashboard_pane;

    @FXML
    private Button emergency_services;

    @FXML
    private Button expense_calculator;

    @FXML
    private Button features_btn;

    @FXML
    private ImageView features_img;

    @FXML
    private Pane features_pane;

    @FXML
    private Label label_address;

    @FXML
    private Label label_email;

    @FXML
    private Label label_name;

    @FXML
    private Label label_phone;

    @FXML
    private ImageView logo_img;

    @FXML
    private Button logout_btn;

    @FXML
    private ImageView logout_img;

    @FXML
    private Button pay_bills;

    @FXML
    private Pane search_bar;

    @FXML
    private Button service_providers;

    @FXML
    private Button settings_btn;

    @FXML
    private ImageView settings_img;

    @FXML
    private Button tickets;

    @FXML
    private Button to_do_list;

    @FXML
    private ImageView user_icon;

    @FXML
    private ImageView user_img;

    @FXML
    private Pane user_info;

    @FXML
    private Button weather;
    @FXML
    private Pane settings_pane;
    @FXML
    private  String[] menus= {"Update Account","Settings","Logout"};
    @FXML
    private MenuButton menu;
    @FXML
    private TextField address;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private Pane todo_pane;
    @FXML
    private TextArea todo_list;
    @FXML
    private TextArea dashboard_todo;
    public Connection connect;
    public PreparedStatement prepare;
    public ResultSet result;
    public Statement statement;

    public Connection connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect
                    = DriverManager.getConnection("jdbc:mysql://localhost/useraccount", "root", "");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Connection connectDB1() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect
                    = DriverManager.getConnection("jdbc:mysql://localhost/provideraccount", "root", "");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void update_info() throws SQLException {
        int userId = getUserIdFromUsersTable(); // Implement this method to get the user's ID
        // Update labels with new information
        label_name.setText(name.getText());
        label_address.setText(address.getText());
        label_phone.setText(phone.getText());
        label_email.setText(email.getText());

        // Display success message
        alertMessage alert = new alertMessage();
        alert.successMessage("Successfully updated information");

        // Connect to the database
        connect = connectDB();
        try {
            String email = getUserIdFromUsersTableByEmail();
            // Get the user's ID from the users table based on some criteria like username


            // Define the SQL query to check if data exists for the user in the info table
            String checkDataQuery = "SELECT * FROM info WHERE user_id = ?";
            prepare = connect.prepareStatement(checkDataQuery);
            prepare.setInt(1, userId);

            result = prepare.executeQuery();

            // Check if data exists for the user in the info table
            if (result.next()) {
                // Data exists, so load existing information into labels
                label_name.setText(result.getString("name"));
                label_address.setText(result.getString("address"));
                label_phone.setText(result.getString("phone"));
                label_email.setText(email);
                // You may also load other information into respective labels if needed

                // Display message indicating existing information loaded
                alertMessage alert2 = new alertMessage();
                alert2.successMessage("Existing information loaded successfully");
            } else {
                // No data exists, so update labels with new information
                label_name.setText(name.getText());
                label_address.setText(address.getText());
                label_phone.setText(phone.getText());
                // You may also update other labels with new information if needed

                // Display success message
                alertMessage alert3 = new alertMessage();
                alert3.successMessage("New information updated successfully");

                // Insert new data into the info table
                // Define the SQL query to insert data into the info table
                String insertData = "INSERT INTO info (user_id, name, address, phone) VALUES (?, ?, ?, ?)";
                prepare = connect.prepareStatement(insertData);
                prepare.setInt(1, userId);
                prepare.setString(2, name.getText());
                prepare.setString(3, address.getText());
                prepare.setString(4, phone.getText());
                // Execute the PreparedStatement to insert the data into the database
                prepare.executeUpdate();
            }
            result.close();
            prepare.close();


        } catch (SQLException e) {
            e.printStackTrace(); // Print any exceptions that occur during execution
        } finally {
            // Close the ResultSet and PreparedStatement in the finally block to release resources
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (prepare != null) {
                try {
                    prepare.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

// Clear text fields
        name.clear();
        address.clear();
        email.clear();
        phone.clear();

    }

    // Method to get the user's ID from the users table
    // Method to get the user's ID from the users table
    @FXML
    private int getUserIdFromUsersTable() throws SQLException {
        int userId = 0; // Initialize the userId variable

        // Connect to the database
        connect = connectDB();
        try {
            // Define the SQL query to retrieve the user's ID from the users table
            String query = "SELECT user_id FROM users WHERE username=?"; // Replace <criteria> with the appropriate condition

            // Create a PreparedStatement to execute the SQL query
            prepare = connect.prepareStatement(query);
            prepare.setString(1, name.getText());
            // Execute the query
            result = prepare.executeQuery();

            // Check if the ResultSet contains any rows
            if (result.next()) {
                // Retrieve the user ID from the ResultSet
                userId = result.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print any exceptions that occur during execution
        }



        return userId; // Return the user's ID retrieved from the users table
    }

    @FXML
    private String getUserIdFromUsersTableByEmail() throws SQLException {
        String userId=null ; // Initialize the userId variable

        // Connect to the database
        connect = connectDB();
        try {
            // Define the SQL query to retrieve the user's ID from the users table based on email
            String query = "SELECT email FROM users WHERE username=?";

            // Create a PreparedStatement to execute the SQL query
            prepare = connect.prepareStatement(query);
            prepare.setString(1, name.getText());

            // Execute the query
            result = prepare.executeQuery();

            // Check if the ResultSet contains any rows
            if (result.next()) {
                // Retrieve the email from the ResultSet
                userId = result.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print any exceptions that occur during execution
        }

        return (userId); // Return the user's ID retrieved from the users table
    }





    @FXML
    public void show_features(){
        dashboard_pane.setVisible(false);
        features_pane.setVisible(true);
        settings_pane.setVisible(false);
        todo_pane.setVisible(false);
    }
    @FXML
    public void show_dashboard(){
        dashboard_pane.setVisible(true);
        features_pane.setVisible(false);
        settings_pane.setVisible(false);
        todo_pane.setVisible(false);

    }
    @FXML
    public  void update_acc(){
        dashboard_pane.setVisible(false);
        features_pane.setVisible(false);
        settings_pane.setVisible(true);
        todo_pane.setVisible(false);
    }
    @FXML
    public void set_todo(){
        dashboard_todo.setText(todo_list.getText());
        alertMessage alert = new alertMessage();
        alert.successMessage("Succesfully updated To-Do List");
    }
    @FXML
    public void clear_todo(){
        dashboard_todo.clear();
        todo_list.clear();
    }
    @FXML
    public void to_do_list(ActionEvent event){
        dashboard_pane.setVisible(false);
        features_pane.setVisible(false);
        settings_pane.setVisible(false);
        todo_pane.setVisible(true);

    }
    @FXML
    public void emergency_services() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("emergency.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        // TO SHOW OUR MAIN FORM
        stage.show();
        //To HIDE DASHBOARD
        //weather.getScene().getWindow().hide();



    }
    @FXML
    public void pay_bills(){

    }
    @FXML
    public void service_providers () throws IOException {
       /* Parent root = FXMLLoader.load(getClass().getResource("userservice.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        // TO SHOW OUR MAIN FORM
        stage.show();
        //To HIDE DASHBOARD
        //expense_calculator.getScene().getWindow().hide();


        */

        chat_server server = new chat_server();

        server.main();

    }
    @FXML
    public void expense_calculator() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("expensecal.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        // TO SHOW OUR MAIN FORM
        stage.show();
        //To HIDE DASHBOARD
        //expense_calculator.getScene().getWindow().hide();
    }


    @FXML
    public void tickets(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tickets.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        // TO SHOW OUR MAIN FORM
        stage.show();
        //To HIDE DASHBOARD
        //tickets.getScene().getWindow().hide();
    }
    @FXML
    public void logout(ActionEvent event) throws IOException {
        // Delete data from the session table
        deleteSessionData();

        // Redirect to the login page
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        // TO SHOW OUR MAIN FORM
        stage.show();
        //To HIDE DASHBOARD
        logout_btn.getScene().getWindow().hide();
    }

    @FXML
    private void deleteSessionData() {
        // Connect to the database
        connect = connectDB();
        try {
            // Define the SQL query to delete data from the session table
            String deleteSessionQuery = "DELETE FROM session";

            // Create a Statement to execute the SQL query
            statement = connect.createStatement();

            // Execute the query to delete data from the session table
            statement.executeUpdate(deleteSessionQuery);
        } catch (SQLException e) {
            e.printStackTrace(); // Print any exceptions that occur during execution
        }
        connect = connectDB1();
        try {
            // Define the SQL query to delete data from the session table
            String deleteSessionQuery = "DELETE FROM message";

            // Create a Statement to execute the SQL query
            statement = connect.createStatement();

            // Execute the query to delete data from the session table
            statement.executeUpdate(deleteSessionQuery);
        } catch (SQLException e) {
            e.printStackTrace(); // Print any exceptions that occur during execution
        }
    }


    @FXML
    public void car_rent(){
        chat_server server = new chat_server();

        server.main();
    }
    @FXML
    public void weather(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("weather.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        // TO SHOW OUR MAIN FORM
        stage.show();
        //To HIDE DASHBOARD
        //weather.getScene().getWindow().hide();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }


}
