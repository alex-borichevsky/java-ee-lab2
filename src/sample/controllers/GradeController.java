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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import sample.DataBaseHandlerInterface;
import sample.Grade;
import sample.StartServer;
import sample.Student;

import javax.xml.stream.XMLStreamException;

public class GradeController {
    Registry registry = LocateRegistry.getRegistry(2732);

    DataBaseHandlerInterface dataBaseHandler = (DataBaseHandlerInterface) registry.lookup(StartServer.UNIQUE_BINDING_NAME);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Grade, Integer> examId;

    @FXML
    private TextField examIdField;

    @FXML
    private TableColumn<Grade, Integer> id;

    @FXML
    private TextField idField;

    @FXML
    private TableColumn<Grade, Integer> mark;

    @FXML
    private TextField markField;

    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<Grade, Integer> studentId;

    @FXML
    private TextField studentIdField;

    @FXML
    private TableView<Grade> table;
    private Connection conn = null;
    ObservableList<Grade> list = FXCollections.observableArrayList();

    public GradeController() throws RemoteException, NotBoundException {
    }

    @FXML
    void initialize() throws SQLException, RemoteException {

                ArrayList<Grade> arr =dataBaseHandler.getGrades();
                for(Grade gr : arr){
                    list.add(new Grade(gr.id, gr.mark, gr.studentId, gr.examId));
                }
                table.setEditable(true);
                id.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("id"));
                id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                mark.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("mark"));
                mark.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                mark.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Grade, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Grade, Integer> studentStringCellEditEvent) {
                        Grade gr = studentStringCellEditEvent.getRowValue();
                        int m = studentStringCellEditEvent.getNewValue();
                        try {
                            dataBaseHandler.updateGradeMark(gr.getId(), m);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    }
                });
                studentId.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("studentId"));
                studentId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

                examId.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("examId"));
                examId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


                table.setItems(list);

        addButton.setOnAction(actionEvent -> {
            int id = Integer.parseInt(idField.getText());
            int mark = Integer.parseInt(markField.getText());
            int stId = Integer.parseInt(studentIdField.getText());
            int exId = Integer.parseInt(examIdField.getText());
            Grade gra = new Grade(id, mark, stId, exId);
            try{
                dataBaseHandler.addGrade(gra);
                list.add(gra);
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
            Grade gr = table.getSelectionModel().getSelectedItem();
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            System.out.println(gr.id);

            try{
                dataBaseHandler.removeGrade(gr.id);

            } catch ( RemoteException throwables) {
                throwables.printStackTrace();
            }
        });
    }


}