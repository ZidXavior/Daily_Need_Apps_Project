package org.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketsController implements Initializable {
    @FXML
    public WebEngine webEngine=null;

    @FXML
    private AnchorPane buttonsPane;

    @FXML
    private WebView webview;

    @FXML
    private AnchorPane webviewPane;

    @FXML
    void airplaneWeb(ActionEvent event) {
        webEngine.load("https://booking.salamair.com/en/search");
        buttonsPane.setVisible(false);
        webviewPane.setVisible(true);
    }

    @FXML
    void busWeb(ActionEvent event) {
        webEngine.load("https://www.shohoz.com/bus-tickets");
        buttonsPane.setVisible(false);
        webviewPane.setVisible(true);
    }

    @FXML
    void trainWeb(ActionEvent event) {
        webEngine.load("https://eticket.railway.gov.bd/");
        buttonsPane.setVisible(false);
        webviewPane.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webview.setZoom(0.9);
        webEngine = webview.getEngine();
    }
}
