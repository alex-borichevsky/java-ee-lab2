package sample;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class Exam implements Serializable {

    public static  String createExam = "create table exam(\n" +
            "id int primary key auto_increment,\n" +
            "title varchar(20) not null\n" +
            ");";

    public String title;
    public int id;
    public Exam(int id, String title){
        this.id = id;
        this.title = title;
    }
    public void addExam(Connection conn, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, this.id);
        preparedStatement.setString(2, this.title);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
