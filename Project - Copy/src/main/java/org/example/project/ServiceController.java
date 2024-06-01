package org.example.project;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class ServiceController implements Initializable {
        @FXML
        private WebView webview;
        @FXML
        private WebEngine webEngine;

        @FXML
        private Button car_rent;

        @FXML
        private Pane car_rent_pane;

        @FXML
        private Button emergency;

        @FXML
        private Pane emergency_pane;

        @FXML
        private Button other;

        @FXML
        private Pane other_pane;

    public Connection connect;
    public PreparedStatement prepare;
    public ResultSet result;
    public Statement statement;
    public Connection connectDB() {
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
        void to_car_rent(ActionEvent event) {
            Random random = new Random();
            car_rent_pane.setVisible(true);
            other_pane.setVisible(false);
            emergency_pane.setVisible(false);

            connect=connectDB();
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
            chat_client client =new chat_client();
            client.main();
        }

        @FXML
        void to_emergency(ActionEvent event) {
            emergency_pane.setVisible(true);
            other_pane.setVisible(false);
            car_rent_pane.setVisible(false);
            webEngine.load("http://localhost/providermap/new.html");

        }

        @FXML
        void to_other(ActionEvent event) {
            Random random = new Random();
            emergency_pane.setVisible(false);
            other_pane.setVisible(true);
            car_rent_pane.setVisible(false);
            connect=connectDB();
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

            chat_client client =new chat_client();
            client.main();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webview.setZoom(0.9);
        webEngine = webview.getEngine();
    }
}

