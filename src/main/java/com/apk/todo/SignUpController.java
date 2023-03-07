package com.apk.todo;

import com.apk.todo.database.DatabaseHandler;
import com.apk.todo.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpName;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpLogin;

    @FXML
    private PasswordField signUpPassword;
    @FXML
    private Button signUpLogginButton;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event ->{ createUser();
        });
        signUpLogginButton.setOnAction(event -> {
            signUpLogginButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));
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


    }
private void createUser(){
    DatabaseHandler databaseHandler = new DatabaseHandler();
    String name = signUpName.getText();
    String login = signUpLogin.getText();
    String password = signUpPassword.getText();
    User user = new User(name,login,password);
    try {
        databaseHandler.createUser(user);
    } catch (SQLException e) {
        e.printStackTrace();
    }


}
}