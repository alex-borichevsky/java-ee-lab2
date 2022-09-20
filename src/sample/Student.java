package sample;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
public class Student implements Serializable {
    public static String createStudent = "create table student(\n" +
            "id int primary key auto_increment,\n" +
            "firstname varchar(20) not null,\n" +
            "lastname varchar(20) not null,\n" +
            "groupnumber int not null\n" +
            ");";

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String firstName;
    public String secondName;
    public int groupNumber;
    public Student(int id, String firstName, String secondName, int groupNumber){
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.groupNumber = groupNumber;
    }
}
