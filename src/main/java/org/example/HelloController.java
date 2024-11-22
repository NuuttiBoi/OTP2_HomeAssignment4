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
    public Button updateButton;
    private DbConnect dbConnect;
    private String userId, userName;
    private Alert alert;
    public void initialize(){
        dbConnect = new DbConnect();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }

    public void handleAdd() {
        userName = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String city = cityField.getText();
        dbConnect.addUser(userName, age, city);
        alert.setContentText("A new user was added successfully");
        alert.showAndWait();
    }
    public void handleDelete(){
        userName = nameField.getText();
        dbConnect.deleteUser(userName);
        alert.setContentText("A user was deleted successfully");
        alert.showAndWait();
    }
    public void handleUpdate(){
        userName = nameField.getText();
        int newAge = Integer.parseInt(ageField.getText());
        String newCity = cityField.getText();
        if(userName != null && newCity != null){
            dbConnect.updateUser(userName, newAge, newCity);
            alert.setContentText("A users information was updated successfully");
            alert.showAndWait();
        } else {
            alert.setContentText("You need to fill the required fields in order to update " +
                    "a users information (name, age, city)");
            alert.showAndWait();
        }
    }
    public void handleRead(){
        userName = nameField.getText();
        alert.setHeaderText("A user was found with the username " + userName + "!");
        alert.setContentText(dbConnect.readUser(userName));
        alert.showAndWait();
    }




}