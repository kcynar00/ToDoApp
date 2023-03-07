package com.apk.todo;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddItemController {

    public static int userId;
    @FXML
    private ResourceBundle resources;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private URL location;

    @FXML
    private Button addButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button logoutAddItemForm;


    @FXML
    void initialize() {
        addButton.setOnAction(actionEvent -> {
            addButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("additemForm.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });


    }}