package org.example.project;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

public class Userservice implements Initializable {
    public Connection connect;
    public PreparedStatement prepare;
    public ResultSet result;
    public Statement statement;
    @FXML
    private AnchorPane ap_main;

    @FXML
    private Button button_send;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private TextField tf_message;

    @FXML
    private VBox vbox_message;




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





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random random = new Random();
        connect=connectDB1();
        try{
            // Generate a random 10-digit text
            StringBuilder randomText = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                randomText.append(random.nextInt(10)); // Append a random digit (0-9)
            }

            // Define the SQL query to insert the random text into the 'message' table
            String insertQuery = "INSERT INTO message (mess) VALUES (?)";

            // Create a PreparedStatement to execute the SQL query
            prepare = connect.prepareStatement(insertQuery);
            prepare.setString(1, randomText.toString());

            // Execute the PreparedStatement to insert the data into the database
            prepare.executeUpdate();

            // Close the PreparedStatement
            prepare.close();
        }catch (Exception e){}



    }


}
