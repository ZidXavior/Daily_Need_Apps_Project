package org.example.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Expensecal {
    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private Label lblResult;
    @FXML private TextField monthlys;
    @FXML private TextField spendings;
    @FXML private TextField remaining;

    private double x, y;
    private double num1 = 0;
    private String operator = "+";

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn",""));
        lblResult.setText(Double.parseDouble(lblResult.getText())==0?String.valueOf((double)value):String.valueOf(Double.parseDouble(lblResult.getText())*10+value));
    }
    @FXML
    // Declare remainingAmount as a class-level variable to persist its value across method calls
    int remainingAmount = 0;
    @FXML

    void calculate_expense(ActionEvent event) {
        String dailySpendingText = spendings.getText();
        String monthlySting= monthlys.getText();

        try {
            // Parse the text into an integer
            int dailySpending = Integer.parseInt(dailySpendingText);
            int monthly = Integer.parseInt(monthlySting);

            // Deduct the daily spending from the remaining amount
            remainingAmount += dailySpending;
            int result= monthly-remainingAmount;
            // Update the remaining text field with the updated value
            remaining.setText(String.valueOf(monthly-remainingAmount));
            if(result<=0){
                alertMessage alert = new alertMessage();
                alert.errorMessage("All income spended");
                spendings.clear();
                remaining.clear();
                monthlys.clear();
            }
        } catch (NumberFormatException e) {
            // Handle the case where the text is not a valid integer
            System.out.println("Invalid input: Not a valid integer");
        }

        // Clear the spendings TextField after calculation
        spendings.clear();
    }


    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        if(symbol.equals("Equals")) {
            double num2 = Double.parseDouble(lblResult.getText());
            switch (operator) {
                case "+" -> lblResult.setText((num1+num2) + "");
                case "-" -> lblResult.setText((num1-num2) + "");
                case "*" -> lblResult.setText((num1*num2) + "");
                case "/" -> lblResult.setText((num1/num2) + "");
            }
            operator = ".";
        }
        else if(symbol.equals("Clear")) {
            lblResult.setText(String.valueOf(0.0));
            operator = ".";
        }
        else {
            switch (symbol) {
                case "Plus" -> operator = "+";
                case "Minus" -> operator = "-";
                case "Multiply" -> operator = "*";
                case "Divide" -> operator = "/";
            }
            num1 = Double.parseDouble(lblResult.getText());
            lblResult.setText(String.valueOf(0.0));
        }
    }
}
