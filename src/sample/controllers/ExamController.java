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
import sample.Exam;
import sample.StartServer;
import sample.Student;

import javax.xml.stream.XMLStreamException;

public class ExamController {
    Registry registry = LocateRegistry.getRegistry(2732);

    DataBaseHandlerInterface dataBaseHandler = (DataBaseHandlerInterface) registry.lookup(StartServer.UNIQUE_BINDING_NAME);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Exam, Integer> id;

    @FXML
    private TextField idField;

    @FXML
    private Button removeButton;

    @FXML
    private TableView<Exam> table;
    ObservableList<Exam> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Exam, String> title;

    @FXML
    private TextField titleField;
    private Connection conn = null;

    public ExamController() throws RemoteException, NotBoundException {
    }

    @FXML
    void initialize() throws SQLException, RemoteException {
                ArrayList<Exam> arr = dataBaseHandler.getExams();
                for(Exam ex : arr){
                    list.add(new Exam(ex.id, ex.title));
                    System.out.println(ex);
                    System.out.println(ex.id + ":" + ex.title);
                }
                table.setEditable(true);
                id.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("id"));
                id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                title.setCellValueFactory(new PropertyValueFactory<Exam, String>("title"));
                title.setCellFactory(TextFieldTableCell.forTableColumn());
                table.setItems(list);
                title.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Exam, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Exam, String> studentStringCellEditEvent) {
                        Exam ex = studentStringCellEditEvent.getRowValue();
                        String t = studentStringCellEditEvent.getNewValue();
                        try {
                            dataBaseHandler.updateExamName(ex.getId(), t);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                    }
                });

        addButton.setOnAction(actionEvent -> {
            String tit = titleField.getText();
            int id = Integer.parseInt(idField.getText());
            Exam ex = new Exam(id, tit);
            try{
                dataBaseHandler.addExam(ex);
                list.add(ex);
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
            Exam ex = table.getSelectionModel().getSelectedItem();
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            System.out.println(ex.id);

            try{
                dataBaseHandler.removeExam(ex.id);

            } catch ( RemoteException throwables) {
                throwables.printStackTrace();
            }
        });
    }

}
