package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HelloController {
    public Label idText;
    public Label nameText;
    public Label ageText;
    public Label cityText;
    public Button addButton;
    public TextField idField;
    public Button deleteButton;
    public TextField nameField;
    public TextField ageField;
    public TextField cityField;
    public Button readButton;
    private DbConnect dbConnect;
    private String userId, userName;
    private Alert alert;
    public void initialize(){
        dbConnect = new DbConnect();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }

    public void handleAdd(ActionEvent actionEvent) {
        dbConnect.addUser();
        alert.setContentText("A new user was added successfully");
        alert.showAndWait();
    }
    public void handleDelete(ActionEvent actionEvent){
        userName = nameField.getText();
        dbConnect.deleteUser(userName);
        alert.setContentText("A user was deleted successfully");
        alert.showAndWait();
    }
    public void handleUpdate(ActionEvent actionEvent){
        dbConnect.updateUser();
    }
    public void handleRead(ActionEvent actionEvent){
        userName = nameField.getText();
        alert.setHeaderText("A user was found with the username " + userName + "!");
        alert.setContentText(dbConnect.readUser(userName));
        alert.showAndWait();
    }




}