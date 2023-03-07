package com.apk.todo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.apk.todo.database.DatabaseHandler;
import com.apk.todo.model.Task;
import com.apk.todo.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddItemFormController {
    private DatabaseHandler databaseHandler;
    @FXML
    private ResourceBundle resources;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private URL location;

    @FXML
    private Button addTasksButton;

    @FXML
    private Button showTasksButton;

    @FXML
    private TextField tasksText;
    @FXML
    private TextField tasksTitle;
    @FXML
    private Button logoutAddItemForm;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        addTasksButton.setOnAction(event -> {
            boolean addedTask;
            if (saveTaskButtonPressed(databaseHandler)) {
                tasksTitle.setText("");

                addedTask = true;
            }else{
                addedTask = false;
            }
        });

        showTasksButton.setOnAction(actionEvent -> {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("tasks.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = (Stage) showTasksButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        });
        logoutAddItemForm.setOnAction(actionEvent -> {
            logoutAddItemForm.getScene().getWindow().hide();
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

    private boolean saveTaskButtonPressed(DatabaseHandler databaseHandler) {
        Task task = new Task();
        User user = new User();

        String taskTitle = tasksTitle.getText().trim();

        if (!taskTitle.equals("")) {

            task.setUserid(user.getUserID());
            task.setData(taskTitle);


            databaseHandler.insertTask(task);
            return true;

        } else {
            return false;
        }
    }
}





