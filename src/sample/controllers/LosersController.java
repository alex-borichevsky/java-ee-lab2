package sample.controllers;

import java.io.FileNotFoundException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import sample.DataBaseHandlerInterface;
import sample.StartServer;
import sample.Student;

import javax.xml.stream.XMLStreamException;

public class LosersController {
    Registry registry = LocateRegistry.getRegistry(2732);

    DataBaseHandlerInterface dataBaseHandler = (DataBaseHandlerInterface) registry.lookup(StartServer.UNIQUE_BINDING_NAME);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Student, String> firstName;

    @FXML
    private TableColumn<Student, Integer> group;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<Student , String> secondName;

    @FXML
    private TableView<Student> table;
    ObservableList<Student> list = FXCollections.observableArrayList();
    private Connection conn = null;

    public LosersController() throws RemoteException, NotBoundException {
    }

    @FXML
    void initialize() throws SQLException, RemoteException {

                ArrayList<Student> arr = dataBaseHandler.getLosers();
                System.out.println(arr);
                for(Student st : arr){
                    list.add(new Student(st.id, st.firstName, st.secondName, st.groupNumber));
                }
                id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
                id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
                firstName.setCellFactory(TextFieldTableCell.forTableColumn());
                secondName.setCellValueFactory(new PropertyValueFactory<Student, String>("secondName"));
                secondName.setCellFactory(TextFieldTableCell.forTableColumn());
                group.setCellValueFactory(new PropertyValueFactory<Student, Integer>("groupNumber"));
                group.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                table.setItems(list);


        removeButton.setOnAction(actionEvent -> {


            try{
                dataBaseHandler.deleteLosers();
                table.getItems().clear();

            } catch (RemoteException throwables) {
                throwables.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        });

    }

    }


