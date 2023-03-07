package com.apk.todo;

import com.apk.todo.model.User;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.apk.todo.database.DatabaseHandler;
import com.apk.todo.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CellController extends JFXListCell<Task> {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView iconImageView;

    @FXML
    private Button deleteButton;
    @FXML
    private Label descriptionLabel;

    private FXMLLoader fxmlLoader;

    private DatabaseHandler databaseHandler;
    private User user;

    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task myTask, boolean empty) {
        user = new User();
        super.updateItem(myTask, empty);

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().
                        getResource("cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //task label and description label setting
            descriptionLabel.setText(myTask.getData());


            int taskId = myTask.getDataid();

            //this will remove the clicked item
            deleteButton.setOnMouseClicked(event -> {
                databaseHandler = new DatabaseHandler();
                getListView().getItems().remove(getItem());
                try {
                    databaseHandler.deleteTask(user.getUserID(), taskId);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });

            setText(null);
            setGraphic(rootAnchorPane);

        }
    }

}
