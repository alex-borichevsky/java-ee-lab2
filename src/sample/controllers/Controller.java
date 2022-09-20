package sample.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.DataBaseHandlerInterface;
import sample.StartServer;
import sample.Student;

public class Controller {
    Registry registry = LocateRegistry.getRegistry(2732);

    DataBaseHandlerInterface dataBaseHandler = (DataBaseHandlerInterface) registry.lookup(StartServer.UNIQUE_BINDING_NAME);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteLosersButton;

    @FXML
    private Button examsButton;

    @FXML
    private Button gradesButton;

    @FXML
    private Button losersButton;

    @FXML
    private Button studentsButton;
    public static Statement statement;
    public static Connection conn;

    public Controller() throws RemoteException, NotBoundException {
    }

    @FXML
    void initialize() throws RemoteException, NotBoundException {
     studentsButton.setOnAction(actionEvent -> {
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("../views/students.fxml"));
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
        examsButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/exams.fxml"));
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
        gradesButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/grade.fxml"));
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
        losersButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/losers.fxml"));
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
}