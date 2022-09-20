package sample;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class Grade implements Serializable {
    public static String createGrade = "create table grade(\n" +
            "id int primary key auto_increment,\n" +
            "mark int not null,\n" +
            "studentId int,\n" +
            "examId int,\n" +
            "foreign key(studentId) references student(id),\n" +
            "foreign key(examId) references exam(id)\n" +
            ")";
    public int mark;
    public int studentId;
    public int examId;
    public int id;

    public Grade(int id, int mark, int studentId, int examId){
        this.id = id;
        this.mark = mark;
        this.studentId = studentId;
        this.examId = examId;
    }
    public void addGrade(Connection conn, PreparedStatement preparedStatement)throws SQLException {
        preparedStatement.setInt(1, this.id);
        preparedStatement.setInt(2, this.mark);
        preparedStatement.setInt(3, this.studentId);
        preparedStatement.setInt(4, this.examId);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
