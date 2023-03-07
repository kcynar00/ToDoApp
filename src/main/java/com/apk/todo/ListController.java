package com.apk.todo;

import com.apk.todo.model.User;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import com.apk.todo.database.DatabaseHandler;
import com.apk.todo.model.Task;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListController {

    @FXML
    private JFXListView<Task> listTasks;
    @FXML
    private Button backButton;


    private ObservableList<Task> tasks;

    private DatabaseHandler databaseHandler;
    private User user;


    @FXML
    void initialize() throws SQLException {

        tasks = FXCollections.observableArrayList();

        databaseHandler = new DatabaseHandler();
        user = new User();

        ResultSet resultSet = databaseHandler.getTaskByUser(user.getUserID());

        while (resultSet.next()) {
            Task task = new Task();
            task.setUserid(resultSet.getInt("userid"));
            task.setDataid(resultSet.getInt("dataid"));
            task.setData(resultSet.getString("data"));

            tasks.addAll(task);
        }

        listTasks.setItems(tasks);

        listTasks.setCellFactory(CellController -> new CellController());

        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
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

    }
}


