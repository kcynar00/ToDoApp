package com.apk.todo;

import com.apk.todo.animations.Shaker;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginLogin;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    private TextField LoginUsername;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws IOException {
        databaseHandler = new DatabaseHandler();

        LoginLogin.setOnAction(event ->{
            String loginText = LoginUsername.getText().trim();
            String loginPwd = LoginPassword.getText().trim();
            User user = new User();
            user.setLogin(loginText);
            user.setPassword(loginPwd);
            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;
                try {
                    while (userRow.next()) {
                        counter++;
                        user.setUserID(userRow.getInt("userID"));
                    }

                    if (counter == 1) {
                        showAddItemScreen();
                    }else{
                        Shaker userNameshkr = new Shaker(LoginUsername);
                        Shaker passwordshkr = new Shaker(LoginPassword);
                        userNameshkr.shake();
                        passwordshkr.shake();
                    }
                }catch (SQLException e) {
                     e.printStackTrace();
                }
            });
        LoginSignUpButton.setOnAction(actionEvent -> {
            LoginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sign-up.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void showAddItemScreen(){
    LoginLogin.getScene().getWindow().hide();
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
}


    }
