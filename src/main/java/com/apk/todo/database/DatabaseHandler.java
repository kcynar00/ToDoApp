package com.apk.todo.database;
import com.apk.todo.model.Task;
import com.apk.todo.model.User;

import java.sql.*;

import static com.apk.todo.AddItemController.userId;

public class DatabaseHandler extends Configure {
    Connection dbConnection;
    private boolean userCreated;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void createUser(User user) throws SQLException {
        String insert = "INSERT INTO " + Constant.USERS_TABLE + "(" + Constant.USERS_NAME + "," + Constant.USERS_LOGIN + "," + Constant.USERS_PASSWORD + ")" +
                "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        if (!user.getLogin().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Constant.USERS_TABLE + " WHERE " +
                    Constant.USERS_LOGIN + "=?" + " AND " + Constant.USERS_PASSWORD
                    + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,user.getLogin());
                preparedStatement.setString(2,user.getPassword());
                resultSet = preparedStatement.executeQuery();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
System.out.println("error");
        }
    return resultSet;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public ResultSet getTaskByUser(int userId) {
        ResultSet resultTasks = null;

        String query = "SELECT * FROM " + Constant.DATA_TABLE + " WHERE " +
                Constant.USERS_ID + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

            preparedStatement.setInt(1, userId);

            resultTasks = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultTasks;
    }



    public void insertTask(Task task) {
        String insert = "INSERT INTO " + Constant.DATA_TABLE + " (" + Constant.USERS_ID + "," + Constant.DATA_DATA + ") "
                + "VALUES(?,?)";

        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert)) {

            preparedStatement.setInt(1, task.getUserid());
            preparedStatement.setString(2, task.getData());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Read
    public int getAllTasks(int userId) throws SQLException, ClassNotFoundException {
//        SELECT COUNT(*) FROM todo.tasks WHERE userid ='1';
        String query = "SELECT COUNT(*) FROM " + Constant.DATA_TABLE + " WHERE " +
                Constant.USERS_ID + "=?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public void deleteTask(int userId, int taskId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Constant.DATA_TABLE + " WHERE " + Constant.USERS_ID +
                "=?" + " AND " + Constant.DATA_ID + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, taskId);
        preparedStatement.execute();
        preparedStatement.close();
    }
}