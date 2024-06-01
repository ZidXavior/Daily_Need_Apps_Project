package org.example.project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML

    private Button mainPageBtn ;

    @FXML
    private Button mainPage_btn1;

    @FXML
    private Button changePass_backBtn;

    @FXML
    private Button changePass_backBtn1;

    @FXML
    private PasswordField changePass_cPassword;

    @FXML
    private PasswordField changePass_cPassword1;

    @FXML
    private AnchorPane changePass_form;

    @FXML
    private AnchorPane changePass_form1;

    @FXML
    private PasswordField changePass_password;

    @FXML
    private PasswordField changePass_password1;

    @FXML
    private Button changePass_proceedBtn;

    @FXML
    private Button changePass_proceedBtn1;

    @FXML
    private Button consumer_btn;

    @FXML
    private TextField forgot_answer;

    @FXML
    private TextField forgot_answer1;

    @FXML
    private Button forgot_backBtn;

    @FXML
    private Button forgot_backBtn1;

    @FXML
    private AnchorPane forgot_form;

    @FXML
    private AnchorPane forgot_form1;

    @FXML
    private Button forgot_proceedBtn;

    @FXML
    private Button forgot_proceedBtn1;

    @FXML
    private ComboBox<?> forgot_selectQuestion;

    @FXML
    private ComboBox<?> forgot_selectQuestion1;

    @FXML
    private TextField forgot_username;

    @FXML
    private TextField forgot_username1;

    @FXML
    private Button login_btn;

    @FXML
    private Button login_btn1;

    @FXML
    private Button login_createAccount;

    @FXML
    private Button login_createAccount1;

    @FXML
    private Hyperlink login_forgotPassword;

    @FXML
    private Hyperlink login_forgotPassword1;

    @FXML
    private AnchorPane login_form;

    @FXML
    private AnchorPane login_form1;

    @FXML
    private PasswordField login_password;

    @FXML
    private PasswordField login_password1;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private CheckBox login_selectShowPassword1;

    @FXML
    private TextField login_showPassword;

    @FXML
    private TextField login_showPassword1;

    @FXML
    private TextField login_username;

    @FXML
    private TextField login_username1;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane main_form1;

    @FXML
    private Button provider_btn;

    @FXML
    private TextField signup_answer;

    @FXML
    private TextField signup_answer1;

    @FXML
    private Button signup_btn;

    @FXML
    private Button signup_btn1;

    @FXML
    private PasswordField signup_cPassword;

    @FXML
    private PasswordField signup_cPassword1;

    @FXML
    private TextField signup_email;

    @FXML
    private TextField signup_email1;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private AnchorPane signup_form1;

    @FXML
    private Button signup_loginAccount;

    @FXML
    private Button signup_loginAccount1;

    @FXML
    private PasswordField signup_password;

    @FXML
    private PasswordField signup_password1;

    @FXML
    private ComboBox<?> signup_selectQuestion;

    @FXML
    private ComboBox<?> signup_selectQuestion1;
    @FXML
    private ComboBox<?> signup_selectQuestion11;

    @FXML
    private TextField signup_username;

    @FXML
    private TextField signup_username1;
    @FXML
    private AnchorPane welcome;
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
    public void changePassword(){

        alertMessage alert = new alertMessage();
        // CHECK ALL FIELDS IF EMPTY OR NOT
        if(changePass_password.getText().isEmpty() || changePass_cPassword.getText().isEmpty()){
            alert.errorMessage("Please fill all blank fields");
        }else if(!changePass_password.getText().equals(changePass_cPassword.getText())){
            // CHECK IF THE PASSWORD AND CONFIRMATION ARE NOT MATCH
            alert.errorMessage("Password does not match");
        }else if(changePass_password.getText().length() < 8){
            // CHECK IF THE LENGTH OF PASSWORD IS LESS THAN TO 8
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        }else{
            // USERNAME IS OUR REFERENCE TO UPDATE THE DATA OF THE USER
            String updateData = "UPDATE users SET password = ?, update_date = ? "
                    + "WHERE username = '" + forgot_username.getText() + "'";

            connect = connectDB();

            try{

                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, changePass_password.getText());

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(2, String.valueOf(sqlDate));

                prepare.executeUpdate();
                alert.successMessage("Succesfully changed Password");

                // LOGIN FORM WILL APPEAR
                signup_form.setVisible(false);
                login_form.setVisible(true);
                forgot_form.setVisible(false);
                changePass_form.setVisible(false);

                login_username.setText("");
                login_password.setVisible(true);
                login_password.setText("");
                login_showPassword.setVisible(false);
                login_selectShowPassword.setSelected(false);

                changePass_password.setText("");
                changePass_cPassword.setText("");

            }catch(Exception e){e.printStackTrace();}

        }

    }

    @FXML
    void changePassword1(ActionEvent event) {
        alertMessage alert = new alertMessage();
        // CHECK ALL FIELDS IF EMPTY OR NOT
        if(changePass_password1.getText().isEmpty() || changePass_cPassword1.getText().isEmpty()){
            alert.errorMessage("Please fill all blank fields");
        }else if(!changePass_password1.getText().equals(changePass_cPassword1.getText())){
            // CHECK IF THE PASSWORD AND CONFIRMATION ARE NOT MATCH
            alert.errorMessage("Password does not match");
        }else if(changePass_password1.getText().length() < 8){
            // CHECK IF THE LENGTH OF PASSWORD IS LESS THAN TO 8
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        }else{
            // USERNAME IS OUR REFERENCE TO UPDATE THE DATA OF THE USER
            String updateData = "UPDATE service_providers SET password = ?, update_date = ? "
                    + "WHERE username = '" + forgot_username1.getText() + "'";

            connect = connectDB1();

            try{

                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, changePass_password1.getText());

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(2, String.valueOf(sqlDate));

                prepare.executeUpdate();
                alert.successMessage("Succesfully changed Password");

                // LOGIN FORM WILL APPEAR
                signup_form1.setVisible(false);
                login_form1.setVisible(true);
                forgot_form1.setVisible(false);
                changePass_form1.setVisible(false);

                login_username1.setText("");
                login_password1.setVisible(true);
                login_password1.setText("");
                login_showPassword1.setVisible(false);
                login_selectShowPassword1.setSelected(false);

                changePass_password1.setText("");
                changePass_cPassword1.setText("");

            }catch(Exception e){e.printStackTrace();}

        }


    }
    @FXML
    void welcomePage(ActionEvent event) {
        welcome.setVisible(true);
        main_form.setVisible(false);
        main_form1.setVisible(false);
    }
    @FXML
    public void forgotPassword() {

        alertMessage alert = new alertMessage();

        if (forgot_username.getText().isEmpty()
                || forgot_selectQuestion.getSelectionModel().getSelectedItem() == null
                || forgot_answer.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            String checkData = "SELECT username, question, answer FROM users "
                    + "WHERE username = ? AND question = ? AND answer = ?";

            connect = connectDB();

            try {

                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, forgot_username.getText());
                prepare.setString(2, (String) forgot_selectQuestion.getSelectionModel().getSelectedItem());
                prepare.setString(3, forgot_answer.getText());

                result = prepare.executeQuery();
                // IF CORRECT
                if (result.next()) {
                    // PROCEED TO CHANGE PASSWORD
                    signup_form.setVisible(false);
                    login_form.setVisible(false);
                    forgot_form.setVisible(false);
                    changePass_form.setVisible(true);
                } else {
                    alert.errorMessage("Incorrect information");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void forgotPassword1(ActionEvent event) {
        alertMessage alert = new alertMessage();

        if (forgot_username1.getText().isEmpty()
                || forgot_selectQuestion1.getSelectionModel().getSelectedItem() == null
                || forgot_answer1.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            String checkData = "SELECT username, question, answer FROM service_providers "
                    + "WHERE username = ? AND question = ? AND answer = ?";

            connect = connectDB1();

            try {

                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, forgot_username1.getText());
                prepare.setString(2, (String) forgot_selectQuestion1.getSelectionModel().getSelectedItem());
                prepare.setString(3, forgot_answer1.getText());

                result = prepare.executeQuery();
                // IF CORRECT
                if (result.next()) {
                    // PROCEED TO CHANGE PASSWORD
                    signup_form1.setVisible(false);
                    login_form1.setVisible(false);
                    forgot_form1.setVisible(false);
                    changePass_form1.setVisible(true);
                } else {
                    alert.errorMessage("Incorrect information");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void goto_mainform(ActionEvent event) {
        main_form.setVisible(true);
        main_form1.setVisible(false);
    }

    @FXML
    void goto_mainform1(ActionEvent event) {
        main_form.setVisible(false);
        main_form1.setVisible(true);
    }


    @FXML
    public void login(ActionEvent event) throws SQLException{

        alertMessage alert = new alertMessage();

        // CHECK IF ALL OR SOME OF THE FIELDS ARE EMPTY
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {
            String selectData = "SELECT * FROM users WHERE "
                    + "username = ? and password = ?"; // users IS OUR TABLE NAME

            connect = connectDB();

            if(login_selectShowPassword.isSelected()){
                login_password.setText(login_showPassword.getText());
            }else{
                login_showPassword.setText(login_password.getText());
            }

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    // ONCE ALL DATA THAT USERS INSERT ARE CORRECT, THEN WE WILL PROCEED TO OUR MAIN FORM

                    alert.successMessage("Successfully Login!");
                    // TO LINK THE MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    // TO SHOW OUR MAIN FORM
                    stage.show();

                    // TO HIDE WINDOW OF LOGIN FORM
                    login_btn.getScene().getWindow().hide();

                } else {
                    // ELSE, THEN ERROR MESSAGE WILL APPEAR
                    alert.errorMessage("Incorrect Username/Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        int userId = 0; // Initialize the userId variable

        try {
            // Define the SQL query to retrieve the user's ID of the logged-in user
            String query = "SELECT user_id FROM users WHERE username=?";

            // Create a PreparedStatement to execute the SQL query
            prepare = connect.prepareStatement(query);
            prepare.setString(1, login_username.getText());

            // Execute the query
            result = prepare.executeQuery();

            // Check if the ResultSet contains any rows
            if (result.next()) {
                // Retrieve the user ID from the ResultSet
                userId = result.getInt("user_id");
                System.out.println("User ID retrieved: " + userId);
            } else {
                // If no rows are returned, print an error message
                System.out.println("No user ID found for username: " + login_username.getText());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print any exceptions that occur during execution
        } finally {
            // Close the ResultSet and PreparedStatement to release resources
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


        try {
            // Retrieving phone number from the info table
            String retrieveDataQuery = "SELECT * FROM info WHERE user_id = ?";
            prepare = connect.prepareStatement(retrieveDataQuery);
            prepare.setInt(1, userId);
            result = prepare.executeQuery();

            if (result.next()) {
                // Retrieve the phone number from the ResultSet
                String phone = result.getString("phone");

                // Inserting phone number into the session table
                String insertData = "INSERT INTO session (user_id, name, phone) VALUES (?, ?, ?)";

                // Create a PreparedStatement to execute the SQL query
                prepare = connect.prepareStatement(insertData);
                prepare.setInt(1, userId);
                prepare.setString(2,result.getString("name"));
                prepare.setString(3,result.getString("phone"));

                // Execute the PreparedStatement to insert the data into the database
                prepare.executeUpdate();
            } else {
                System.out.println("No data found in the 'info' table for the specified user ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @FXML
    void login1(ActionEvent event) {


        alertMessage alert = new alertMessage();

        // CHECK IF ALL OR SOME OF THE FIELDS ARE EMPTY
        if (login_username1.getText().isEmpty() || login_password1.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {
            String selectData = "SELECT * FROM service_providers WHERE "
                    + "username = ? and password = ?"; // users IS OUR TABLE NAME

            connect = connectDB1();

            if(login_selectShowPassword1.isSelected()){
                login_password1.setText(login_showPassword1.getText());
            }else{
                login_showPassword1.setText(login_password1.getText());
            }

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, login_username1.getText());
                prepare.setString(2, login_password1.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    // ONCE ALL DATA THAT USERS INSERT ARE CORRECT, THEN WE WILL PROCEED TO OUR MAIN FORM

                    alert.successMessage("Successfully Login!");
                    // TO LINK THE MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard1.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    // TO SHOW OUR MAIN FORM
                    stage.show();

                    // TO HIDE WINDOW OF LOGIN FORM
                    login_btn1.getScene().getWindow().hide();

                } else {
                    // ELSE, THEN ERROR MESSAGE WILL APPEAR
                    alert.errorMessage("Incorrect Username/Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void register() {

        alertMessage alert = new alertMessage();

        // CHECK IF WE HAVE EMPTY FIELDS
        if (signup_email.getText().isEmpty() || signup_username.getText().isEmpty()
                || signup_password.getText().isEmpty() || signup_cPassword.getText().isEmpty()
                || signup_selectQuestion.getSelectionModel().getSelectedItem() == null
                || signup_answer.getText().isEmpty()) {
            alert.errorMessage("All fields are necessary to be filled");
        } else if (signup_password.getText() == signup_cPassword.getText()) {
            // CHECK IF THE VALUE OF PASSWORD FIELDS IS EQUAL TO CONFIRM PASSWORD
            alert.errorMessage("Password does not match");
        } else if (signup_password.getText().length() < 8) {
            // CHECK IF THE LENGTH OF PASSWORD VALUE IS LESS THAN TO 8
            //, WE WILL CHECK THE CHARACTERS OF PASSWORD
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        } else {
            // CHECK IF THE USERNAME IS ALREADY TAKEN
            String checkUsername = "SELECT * FROM users WHERE username = '"
                    + signup_username.getText() + "'";
            connect = connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkUsername);

                if (result.next()) {
                    alert.errorMessage(signup_username.getText() + " is already taken");
                } else {

                    String insertData = "INSERT INTO users "
                            + "(email, username, password, question, answer, date) "
                            + "VALUES(?,?,?,?,?,?)"; // FIVE (?)

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, signup_email.getText());
                    prepare.setString(2, signup_username.getText());
                    prepare.setString(3, signup_password.getText());
                    prepare.setString(4,
                            (String) signup_selectQuestion.getSelectionModel().getSelectedItem());
                    prepare.setString(5, signup_answer.getText());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");

                    registerClearFields();

                    signup_form.setVisible(false);
                    login_form.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void register1(ActionEvent event) {
        alertMessage alert = new alertMessage();

        // CHECK IF WE HAVE EMPTY FIELDS
        if (signup_email1.getText().isEmpty() || signup_username1.getText().isEmpty()
                || signup_password1.getText().isEmpty() || signup_cPassword1.getText().isEmpty()
                || signup_selectQuestion1.getSelectionModel().getSelectedItem() == null
                || signup_answer1.getText().isEmpty()) {
            alert.errorMessage("All fields are necessary to be filled");
        } else if (signup_password1.getText() == signup_cPassword1.getText()) {
            // CHECK IF THE VALUE OF PASSWORD FIELDS IS EQUAL TO CONFIRM PASSWORD
            alert.errorMessage("Password does not match");
        } else if (signup_password1.getText().length() < 8) {
            // CHECK IF THE LENGTH OF PASSWORD VALUE IS LESS THAN TO 8
            //, WE WILL CHECK THE CHARACTERS OF PASSWORD
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        } else {
            // CHECK IF THE USERNAME IS ALREADY TAKEN
            String checkUsername = "SELECT * FROM service_providers WHERE username = '"
                    + signup_username1.getText() + "'";
            connect = connectDB1();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkUsername);

                if (result.next()) {
                    alert.errorMessage(signup_username1.getText() + " is already taken");
                } else {

                    String insertData = "INSERT INTO service_providers "
                            + "(email, username, password, question, answer, date, category) "
                            + "VALUES(?,?,?,?,?,?,?)"; // FIVE (?)

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, signup_email1.getText());
                    prepare.setString(2, signup_username1.getText());
                    prepare.setString(3, signup_password1.getText());
                    prepare.setString(4,
                            (String) signup_selectQuestion1.getSelectionModel().getSelectedItem());
                    prepare.setString(5, signup_answer1.getText());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.setString(7,
                            (String) signup_selectQuestion11.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");

                    registerClearFields();

                    signup_form1.setVisible(false);
                    login_form1.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void registerClearFields() {
        signup_email.setText("");
        signup_username.setText("");
        signup_password.setText("");
        signup_cPassword.setText("");
        signup_selectQuestion.getSelectionModel().clearSelection();
        signup_answer.setText("");
    }
    public void registerClearFields1(){
        signup_email1.setText("");
        signup_username1.setText("");
        signup_password1.setText("");
        signup_cPassword1.setText("");
        signup_selectQuestion1.getSelectionModel().clearSelection();
        signup_answer1.setText("");
    }
    @FXML
    public void showPassword() {

        if (login_selectShowPassword.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }

    }

    @FXML
    void showPassword1(ActionEvent event) {
        if (login_selectShowPassword1.isSelected()) {
            login_showPassword1.setText(login_password1.getText());
            login_showPassword1.setVisible(true);
            login_password1.setVisible(false);
        } else {
            login_password1.setText(login_showPassword1.getText());
            login_showPassword1.setVisible(false);
            login_password1.setVisible(true);
        }
    }

    @FXML
    public void switchForm(ActionEvent event) {

        // THE REGISTRATION FORM WILL BE VISIBLE
        if (event.getSource() == signup_loginAccount || event.getSource() == forgot_backBtn) {
            signup_form.setVisible(false);
            login_form.setVisible(true);
            forgot_form.setVisible(false);
            changePass_form.setVisible(false);
        } else if (event.getSource() == login_createAccount) { // THE LOGIN FORM WILL BE VISIBLE
            signup_form.setVisible(true);
            login_form.setVisible(false);
            forgot_form.setVisible(false);
            changePass_form.setVisible(false);
        } else if (event.getSource() == login_forgotPassword) {
            signup_form.setVisible(false);
            login_form.setVisible(false);
            forgot_form.setVisible(true);
            changePass_form.setVisible(false);
            // TO SHOW THE DATA TO OUR COMBOBOX
            forgotListQuestion();
        } else if (event.getSource() == changePass_backBtn) {
            signup_form.setVisible(false);
            login_form.setVisible(false);
            forgot_form.setVisible(true);
            changePass_form.setVisible(false);
        }

    }


    @FXML
    void switchForm1(ActionEvent event) {
        // THE REGISTRATION FORM WILL BE VISIBLE
        if (event.getSource() == signup_loginAccount1 || event.getSource() == forgot_backBtn1) {
            signup_form1.setVisible(false);
            login_form1.setVisible(true);
            forgot_form1.setVisible(false);
            changePass_form1.setVisible(false);
        } else if (event.getSource() == login_createAccount1) { // THE LOGIN FORM WILL BE VISIBLE
            signup_form1.setVisible(true);
            login_form1.setVisible(false);
            forgot_form1.setVisible(false);
            changePass_form1.setVisible(false);
        } else if (event.getSource() == login_forgotPassword1) {
            signup_form1.setVisible(false);
            login_form1.setVisible(false);
            forgot_form1.setVisible(true);
            changePass_form1.setVisible(false);
            // TO SHOW THE DATA TO OUR COMBOBOX
            forgotListQuestion();
        } else if (event.getSource() == changePass_backBtn1) {
            signup_form1.setVisible(false);
            login_form1.setVisible(false);
            forgot_form1.setVisible(true);
            changePass_form1.setVisible(false);
        }

    }
    public void forgotListQuestion(){

        List<String> listQ = new ArrayList<>();

        for(String data : questionList){
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        forgot_selectQuestion.setItems(listData);

    }
    public void forgotListQuestion1(){
        List<String> listQ = new ArrayList<>();

        for(String data : questionList){
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        forgot_selectQuestion1.setItems(listData);
    }
    public String[] questionList = {"What is your favorite food?", "What is your favorite color?",
            "What is the name of your pet?", "What is your most favorite sport?"};
    public String[] questionList1 = {"What is your favorite food?", "What is your favorite color?",
            "What is the name of your pet?", "What is your most favorite sport?"};
    public String[] questionList11 = {"Emergency Services", "Others"};
    public void questions() {
        List<String> listQ = new ArrayList<>();

        for (String data : questionList) {
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        signup_selectQuestion.setItems(listData);
    }
    public void questions1() {
        List<String> listQ = new ArrayList<>();

        for (String data : questionList1) {
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        signup_selectQuestion1.setItems(listData);
    }
    public void questions11() {
        List<String> listQ = new ArrayList<>();

        for (String data : questionList11) {
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        signup_selectQuestion11.setItems(listData);
    }


    public void insertUserIdIntoSession(int userId) throws SQLException {
        // Define the SQL query to insert the user's ID into the session table
        connect = connectDB();


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questions();
        questions1();
        questions11();
        forgotListQuestion();
        forgotListQuestion1();

    }

}
