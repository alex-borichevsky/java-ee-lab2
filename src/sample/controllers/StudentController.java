package sample.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import sample.DataBaseHandlerInterface;
import sample.Main;
import sample.StartServer;
import sample.Student;

import javax.xml.stream.XMLStreamException;

import static sample.Main.statement;

public class StudentController {
    Registry registry = LocateRegistry.getRegistry(2732);

    DataBaseHandlerInterface dataBaseHandler = (DataBaseHandlerInterface) registry.lookup(StartServer.UNIQUE_BINDING_NAME);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField groupField;
    @FXML
    private TextField idField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField nameField;
    @FXML
    private TableColumn<Student, String> firstName;

    @FXML
    private TableColumn<Student, Integer> groupNumber;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> lastName;

    @FXML
    private Button removeButton;

    @FXML
    private Button addButton;
    private  Connection conn = null;
    @FXML
    private TableView<Student> table;
    ObservableList<Student> list = FXCollections.observableArrayList();

    public StudentController() throws RemoteException, NotBoundException {
    }

    @FXML
    void initialize() throws SQLException, RemoteException {
        ArrayList<Student> arr = dataBaseHandler.getStudents();
        for(Student st : arr){
            System.out.println(st.id + " : " + st.firstName + " : " + st.secondName + " : " + st.groupNumber + " \n ");
            list.add(new Student(st.id, st.firstName, st.secondName, st.groupNumber));
        }
                table.setEditable(true);
                id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
                id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
                firstName.setCellFactory(TextFieldTableCell.forTableColumn());
                firstName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
                        Student st = studentStringCellEditEvent.getRowValue();
                        String name = studentStringCellEditEvent.getNewValue();
                        try {
                            dataBaseHandler.updateStudentName(st.getId(), name);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    }
                });
                lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("secondName"));
                lastName.setCellFactory(TextFieldTableCell.forTableColumn());
                lastName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
                        Student st = studentStringCellEditEvent.getRowValue();
                        String surname = studentStringCellEditEvent.getNewValue();
                        try {
                            dataBaseHandler.updateStudentSurname(st.getId(), surname);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    }
                });
                groupNumber.setCellValueFactory(new PropertyValueFactory<Student, Integer>("groupNumber"));
                groupNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                groupNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Student, Integer> studentStringCellEditEvent) {
                        Student st = studentStringCellEditEvent.getRowValue();
                        int group = studentStringCellEditEvent.getNewValue();
                        try {
                            dataBaseHandler.updateStudentGroup(st.getId(), group);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    }
                });
                table.setItems(list);
        addButton.setOnAction(actionEvent -> {
            String name = nameField.getText();
            String surname = surnameField.getText();
            int id = Integer.parseInt(idField.getText());
            int groupNumber = Integer.parseInt(groupField.getText());
            Student stud = new Student(id, name, surname, groupNumber);

            try{
                dataBaseHandler.addStudent(stud);
                list.add(new Student(id, name, surname, groupNumber));
                table.setItems(list);

            } catch (RemoteException throwables) {
                throwables.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }

        });
        removeButton.setOnAction(actionEvent -> {
            Student st = table.getSelectionModel().getSelectedItem();
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            System.out.println(st.id);

            try{
                dataBaseHandler.removeStudent(st.id);

            } catch ( RemoteException throwables) {
                throwables.printStackTrace();
            }
        });

    }

}