//package sample;
//import java.sql.*;
//
//public class Main{
//
//    public static void main(String[] args) {
//        try{
//            String url = "jdbc:mysql://localhost:3306/Lesson";
//            String username = "root";
//            String password = "bisquit";
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();;
//            try (Connection conn = DriverManager.getConnection(url, username, password)){
//                Statement statement = conn.createStatement();
////                statement.executeUpdate(sample.Student.createStudent);
////                System.out.println("students created!\n");
////                statement.executeUpdate(sample.Exam.createExam);
////                System.out.println("exams created!\n");
////                statement.executeUpdate(sample.Grade.createGrade);
////                System.out.println("grades created!\n");
////
////                PreparedStatement preparedStatement = conn.prepareStatement("insert into exam set id=?, title=?;");
////                sample.Exam math = new sample.Exam(1, "math");
////                math.addExam(conn, preparedStatement);
////                preparedStatement.executeUpdate();
////
////                sample.Exam chemistry = new sample.Exam(2, "chemistry");
////                chemistry.addExam(conn, preparedStatement);
////                preparedStatement.executeUpdate();
////
//                Student lesha = new Student(5,"lesha", "rubov", 1);
//                PreparedStatement preparedStatement1 = conn.prepareStatement("insert into student set id=?, firstname=?, lastname=?, groupnumber=?;");
//                lesha.addStudent(conn, preparedStatement1);
//                preparedStatement1.executeUpdate();
//
//                Student misha = new Student(2, "misha", "kjwnd", 3);
//                misha.addStudent(conn, preparedStatement1);
//                preparedStatement1.executeUpdate();
////
////
//                Student grisha = new Student(7,"grisha", "jdbjaeb", 5);
//                grisha.addStudent(conn, preparedStatement1);
//                preparedStatement1.executeUpdate();
//
////                sample.Grade grade = new sample.Grade(3, lesha.id, chemistry.id);
////                PreparedStatement preparedStatement2 = conn.prepareStatement("insert into grade set mark=?, studentId=?, examId=?;");
////                grade.addGrade(conn, preparedStatement2);
////                preparedStatement2.executeUpdate();
//
////                sample.Student.getStudents(conn, statement);
////                sample.Exam.getExams(conn, statement);
////              sample.Grade.getGrades(conn, statement);
//                Student.getLosers(conn, statement);
////                sample.Student.deleteLosers(conn, statement);
//            }
//        }
//        catch(Exception ex){
//            System.out.println("Connection failed...");
//
//            System.out.println(ex);
//        }
//
//    }
//}
package sample;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.sql.*;
import java.util.ArrayList;
/**
 * <p>Client part of the RMI</p>
 * implements {@link Application},
 * also can see {@link StartServer}
 * @author Alexey Borichevskiy
 */
public class Main extends Application{
    public static Connection conn;
    public static Statement statement = null;
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}