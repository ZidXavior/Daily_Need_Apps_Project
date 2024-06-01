package org.example.project;

import  javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Emergency implements Initializable {

    @FXML
    private WebView webMap;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the WebView and load a webpage
        WebEngine webEngine = webMap.getEngine();
        webEngine.load("http://localhost/customermap/new.html");

        // Set zoom level
        webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36");
        double zoomFactor = 1.0;
        String setZoom = "document.body.style.zoom = '" + zoomFactor + "'";
        webEngine.executeScript(setZoom);

        // Hide scrollbar
        webMap.setStyle("-fx-background-color: transparent;");
        webMap.setZoom(0.9); // Set zoom level
    }


}