package org.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WeatherController  implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private Pane weatherpane;

    @FXML
    private WebView webView;
    @FXML
    WebEngine webEngine=null;


    @FXML
    void check_weather(ActionEvent event) throws  Exception{
        webEngine.load("http://localhost/Weather/index.html");

        pane.setVisible(false);
        weatherpane.setVisible(true);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      webView.setZoom(1.0);
       webEngine= webView.getEngine();

    }
}
