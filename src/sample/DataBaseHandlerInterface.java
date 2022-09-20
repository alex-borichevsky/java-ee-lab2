package sample;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface DataBaseHandlerInterface extends Remote{
    /**
     * Method removes student
     *
     * @param id                   student id
     * @throws RemoteException rmi exception
     */
    void removeStudent(int id) throws RemoteException;

    /**
     * Method updates student name
     *
     * @param id                   student id
     * @param name                     student name
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException    file exception
     * @throws XMLStreamException    xml exception
     */
    void updateStudentName(int id, String name) throws RemoteException, FileNotFoundException, XMLStreamException;

    /**
     * Method updates student surname
     *
     * @param id                   student id
     * @param surname                     student surname
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException    file exception
     * @throws XMLStreamException    xml exception
     */
    void updateStudentSurname(int id, String surname) throws RemoteException, FileNotFoundException, XMLStreamException;
    /**
     * Method updates student group
     *
     * @param id                   student id
     * @param group                     student group
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException    file exception
     * @throws XMLStreamException    xml exception
     */
    void updateStudentGroup(int id, int group) throws RemoteException, FileNotFoundException, XMLStreamException;
    /**
     * Method updates exam name
     *
     * @param id                   student id
     * @param title                     student title
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException    file exception
     * @throws XMLStreamException    xml exception
     */
    void updateExamName(int id, String title) throws RemoteException, FileNotFoundException, XMLStreamException;

    /**
     * Method updates grades mark
     *
     * @param id                   grade id
     * @param mark                     mark
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException    file exception
     * @throws XMLStreamException    xml exception
     */
    void updateGradeMark(int id, int mark) throws RemoteException, FileNotFoundException, XMLStreamException;

    /**
     * Method adds student
     *
     * @param st                   student object
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException    file exception
     * @throws XMLStreamException    xml exception
     */
    void addStudent(Student st) throws FileNotFoundException, XMLStreamException, RemoteException;
    /**
     * Method return students
     *@return <p> {@code ArrayList<Student>}</p> {@link  DataBaseServerHandler} link to place where is implementation
     ** @throws RemoteException rmi exception
     */
    ArrayList<Student> getStudents() throws RemoteException;
    /**
     * Method return exams
     *@return <p> {@code ArrayList<Exam>}</p>
     * @throws RemoteException rmi exception
     */
    ArrayList<Exam> getExams() throws RemoteException;

    /**
     * Method return grades
     *@return <p> {@code ArrayList<Grade>}</p> {@link  DataBaseServerHandler} link to place where is implementation
     * @throws RemoteException rmi exception
     */
    ArrayList<Grade> getGrades() throws RemoteException;
    /**
     * Method parse students
     * @param fileName                   name of file
     * @throws RemoteException rmi exception
     */
    void parseStudents(String fileName) throws RemoteException;
    /**
     * Method parse exams
     *
     * @param fileName                   name of file
     * @throws RemoteException rmi exception
     */
    void parseExam(String fileName) throws RemoteException;
    /**
     * Method parse grades
     *
     * @param fileName                   name of file
     * @throws RemoteException rmi exception
     */
    void parseGrade(String fileName) throws RemoteException;
    /**
     * Method adds exam
     *
     * @param exam                   exam object
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException    file exception
     * @throws XMLStreamException    xml exception
     */
    void addExam(Exam exam)throws FileNotFoundException, XMLStreamException, RemoteException;
    /**
     * Method adds grade
     *
     * @param grade                   grade object
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException    file exception
     * @throws XMLStreamException    xml exception
     */
    void addGrade(Grade grade)throws FileNotFoundException, XMLStreamException, RemoteException;
    /**
     * Method remove exam
     *
     * @param id                   exam id
     * @throws RemoteException rmi exception
     */
    void removeExam(int id) throws RemoteException;
    /**
     * Method remove grade
     *
     * @param id                   grade id
     * @throws RemoteException rmi exception
     */
    void removeGrade(int id) throws RemoteException;
    /**
     * Method return losers students
     * @throws RemoteException rmi exception
     */
    ArrayList<Student> getLosers() throws RemoteException;
    /**
     * Method delete losers students
     *
     * @throws RemoteException rmi exception
     * @throws FileNotFoundException file exception
     * @throws XMLStreamException xml exception
     */
    void deleteLosers() throws RemoteException, FileNotFoundException, XMLStreamException;
}

