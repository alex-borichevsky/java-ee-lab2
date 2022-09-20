package sample;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

/**
 * <p>ServerHandler of the RMI</p>
 * implements {@link DataBaseHandlerInterface},
 * also can see {@link sample.Main}, {@link StartServer}, {@link DataBaseHandlerInterface}},
 * @author Alexey Borichevskiy
 */
public class DataBaseServerHandler implements DataBaseHandlerInterface {

    private ArrayList<Student> studentsList = new ArrayList<>();
    private ArrayList<Exam> examsList = new ArrayList<>();
    private ArrayList<Grade> gradesList = new ArrayList<>();

    private Connection connection;
    public static final String DB_URL = "jdbc:mysql://localhost:3306/Lesson";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "bisquit";
    DataBaseServerHandler() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            System.out.println("Where is your MySql JDBC Driver?");
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        this.connection = connection;
    }


    @Override
    public void removeStudent(int id) throws RemoteException {
        studentsList = new ArrayList<>();
        parseStudents("students.xml");
        for (int i = 0; i < studentsList.size(); i++) {
            if(studentsList.get(i).getId() == id) {
                studentsList.remove(i);
            }
        }
        try  {
            FileOutputStream out = new FileOutputStream("students.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Students");
            for (Student student : studentsList) {

                writer.writeStartElement("Student");
                writer.writeAttribute("id", Integer.toString(student.getId()));
                writer.writeStartElement("firstname");
                writer.writeCharacters(student.getFirstName());
                writer.writeEndElement();
                writer.writeStartElement("secondname");
                writer.writeCharacters(student.getSecondName());
                writer.writeEndElement();
                writer.writeStartElement("groupnumber");
                writer.writeCharacters(Integer.toString(student.getGroupNumber()));
                writer.writeEndElement();
                writer.writeEndElement();



            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException | XMLStreamException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void updateStudentName(int id, String name) throws RemoteException, FileNotFoundException, XMLStreamException {
        for(Student st: studentsList){
            if(st.getId() == id) {
                st.setFirstName(name);
            }
            updateStudentData();
        }
    }

    @Override
    public void updateStudentSurname(int id, String surname) throws RemoteException, FileNotFoundException, XMLStreamException {
        for(Student st: studentsList){
            if(st.getId() == id) {
                st.setSecondName(surname);
            }
            updateStudentData();
        }
    }

    @Override
    public void updateStudentGroup(int id, int group) throws RemoteException, FileNotFoundException, XMLStreamException {
        for(Student st: studentsList){
            if(st.getId() == id) {
                st.setGroupNumber(group);
            }
            updateStudentData();
        }
    }

    @Override
    public void updateExamName(int id, String title) throws RemoteException, FileNotFoundException, XMLStreamException {
        for(Exam exam: examsList){
            if(exam.getId() == id) {
                exam.setTitle(title);
            }
            updateExamData();
        }
    }

    @Override
    public void updateGradeMark(int id, int mark) throws RemoteException, FileNotFoundException, XMLStreamException {
        for(Grade grade: gradesList){
            if(grade.getId() == id) {
                grade.setMark(mark);
            }
            updateGradeData();
        }
    }

    private void updateGradeData() throws FileNotFoundException, XMLStreamException, RemoteException {
        try  {
            FileOutputStream out = new FileOutputStream("grades.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Grades");
            for (Grade grade : gradesList) {

                writer.writeStartElement("Grade");
                writer.writeAttribute("id", Integer.toString(grade.getId()));
                writer.writeStartElement("mark");
                writer.writeCharacters(Integer.toString(grade.getMark()));
                writer.writeEndElement();
                writer.writeStartElement("studentid");
                writer.writeCharacters(Integer.toString(grade.getStudentId()));
                writer.writeEndElement();
                writer.writeStartElement("examid");
                writer.writeCharacters(Integer.toString(grade.getExamId()));
                writer.writeEndElement();
                writer.writeEndElement();



            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private void updateStudentData() throws FileNotFoundException, XMLStreamException, RemoteException {
        try  {
            FileOutputStream out = new FileOutputStream("students.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Students");
            for (Student student : studentsList) {

                writer.writeStartElement("Student");
                writer.writeAttribute("id", Integer.toString(student.getId()));
                writer.writeStartElement("firstname");
                writer.writeCharacters(student.getFirstName());
                writer.writeEndElement();
                writer.writeStartElement("secondname");
                writer.writeCharacters(student.getSecondName());
                writer.writeEndElement();
                writer.writeStartElement("groupnumber");
                writer.writeCharacters(Integer.toString(student.getGroupNumber()));
                writer.writeEndElement();
                writer.writeEndElement();



            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private void updateExamData() throws FileNotFoundException, XMLStreamException, RemoteException {
        try  {
            FileOutputStream out = new FileOutputStream("exams.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Exams");
            for (Exam exam : examsList) {

                writer.writeStartElement("Exam");
                writer.writeAttribute("id", Integer.toString(exam.getId()));
                writer.writeStartElement("title");
                writer.writeCharacters(exam.getTitle());
                writer.writeEndElement();
                writer.writeEndElement();

            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @Override
    public void addStudent(Student st) throws FileNotFoundException, XMLStreamException, RemoteException {
        studentsList = new ArrayList<>();
        parseStudents("students.xml");
        studentsList.add(st);
        try  {
            FileOutputStream out = new FileOutputStream("students.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Students");
            for (Student student : studentsList) {

                writer.writeStartElement("Student");
                writer.writeAttribute("id", Integer.toString(student.getId()));
                writer.writeStartElement("firstname");
                writer.writeCharacters(student.getFirstName());
                writer.writeEndElement();
                writer.writeStartElement("secondname");
                writer.writeCharacters(student.getSecondName());
                writer.writeEndElement();
                writer.writeStartElement("groupnumber");
                writer.writeCharacters(Integer.toString(student.getGroupNumber()));
                writer.writeEndElement();
                writer.writeEndElement();



            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    /**
     * Method returns students
     *
     * @return <p> {@code ArrayList<Student>}</p>
     * @throws RemoteException rmi exception
     */
    @Override
    public ArrayList<Student> getStudents() throws RemoteException {
        parseStudents("students.xml");
        return studentsList;
    }
    /**
     * Method returns exams
     *
     * @return <p> {@code ArrayList<Exam>}</p>
     * @throws RemoteException rmi exception
     */
    @Override
    public ArrayList<Exam> getExams() throws RemoteException {
        parseExam("exams.xml");
        return examsList;
    }
    /**
     * Method returns grades
     *
     * @return <p> {@code ArrayList<Grade>}</p>
     * @throws RemoteException rmi exception
     */
    @Override
    public ArrayList<Grade> getGrades() throws RemoteException {
        parseGrade("grades.xml");
        return gradesList;
    }

    @Override
    public void parseStudents(String fileName) throws RemoteException {
        studentsList = new ArrayList<>();
        Student student = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // инициализируем reader и скармливаем ему xml файл
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            // проходим по всем элементам xml файла
            while (reader.hasNext()) {
                // получаем событие (элемент) и разбираем его по атрибутам
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Student")) {
                        student = new Student(0, "", "", 0);
                        // Получаем атрибут id для каждого элемента Student
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            student.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("firstname")) {
                        xmlEvent = reader.nextEvent();
                        student.setFirstName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("secondname")) {
                        xmlEvent = reader.nextEvent();
                        student.setSecondName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("groupnumber")) {
                        xmlEvent = reader.nextEvent();
                        student.setGroupNumber(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }
                }
                // если цикл дошел до закрывающего элемента Student,
                // то добавляем считанного из файла студента в список
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Student")) {
                        studentsList.add(student);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void parseExam(String fileName) throws RemoteException {
        examsList = new ArrayList<>();
        Exam exam = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // инициализируем reader и скармливаем ему xml файл
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            // проходим по всем элементам xml файла
            while (reader.hasNext()) {
                // получаем событие (элемент) и разбираем его по атрибутам
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Exam")) {
                        exam = new Exam(0, "");
                        // Получаем атрибут id для каждого элемента Student
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            exam.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("title")) {
                        xmlEvent = reader.nextEvent();
                        exam.setTitle(xmlEvent.asCharacters().getData());
                    }
                }
                // если цикл дошел до закрывающего элемента Student,
                // то добавляем считанного из файла студента в список
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Exam")) {
                        examsList.add(exam);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void parseGrade(String fileName) throws RemoteException {
        gradesList = new ArrayList<>();
        Grade grade = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // инициализируем reader и скармливаем ему xml файл
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            // проходим по всем элементам xml файла
            while (reader.hasNext()) {
                // получаем событие (элемент) и разбираем его по атрибутам
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Grade")) {
                        grade = new Grade(0, 0, 0, 0);
                        // Получаем атрибут id для каждого элемента Student
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            grade.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("mark")) {
                        xmlEvent = reader.nextEvent();
                        grade.setMark(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("studentid")) {
                        xmlEvent = reader.nextEvent();
                        grade.setStudentId(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("examid")) {
                        xmlEvent = reader.nextEvent();
                        grade.setExamId(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }
                }
                // если цикл дошел до закрывающего элемента Student,
                // то добавляем считанного из файла студента в список
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Grade")) {
                        gradesList.add(grade);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void addExam(Exam exam) throws FileNotFoundException, XMLStreamException, RemoteException {
        examsList = new ArrayList<>();
        parseExam("exams.xml");
        examsList.add(exam);
        try  {
            FileOutputStream out = new FileOutputStream("exams.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Exams");
            for (Exam ex : examsList) {

                writer.writeStartElement("Exam");
                writer.writeAttribute("id", Integer.toString(ex.getId()));
                writer.writeStartElement("title");
                writer.writeCharacters(ex.getTitle());
                writer.writeEndElement();
                writer.writeEndElement();



            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void addGrade(Grade grade) throws FileNotFoundException, XMLStreamException, RemoteException {
        gradesList = new ArrayList<>();
        parseGrade("grades.xml");
        gradesList.add(grade);
        try  {
            FileOutputStream out = new FileOutputStream("grades.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Grades");
            for (Grade gr : gradesList) {

                writer.writeStartElement("Grade");
                writer.writeAttribute("id", Integer.toString(gr.getId()));
                writer.writeStartElement("mark");
                writer.writeCharacters(Integer.toString(gr.getMark()));
                writer.writeEndElement();
                writer.writeStartElement("studentid");
                writer.writeCharacters(Integer.toString(gr.getStudentId()));
                writer.writeEndElement();
                writer.writeStartElement("examid");
                writer.writeCharacters(Integer.toString(gr.getExamId()));
                writer.writeEndElement();
                writer.writeEndElement();



            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void removeExam(int id) throws RemoteException {
        examsList = new ArrayList<>();
        parseExam("exams.xml");
        for (int i = 0; i < examsList.size(); i++) {
            if(examsList.get(i).getId() == id) {
                examsList.remove(i);
            }
        }
        try  {
            FileOutputStream out = new FileOutputStream("exams.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Exams");
            for (Exam exam : examsList) {

                writer.writeStartElement("Exam");
                writer.writeAttribute("id", Integer.toString(exam.getId()));
                writer.writeStartElement("title");
                writer.writeCharacters(exam.getTitle());
                writer.writeEndElement();
                writer.writeEndElement();

            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException | XMLStreamException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void removeGrade(int id) throws RemoteException {
        gradesList = new ArrayList<>();
        parseGrade("grades.xml");
        for (int i = 0; i < gradesList.size(); i++) {
            if(gradesList.get(i).getId() == id) {
                gradesList.remove(i);
            }
        }
        try  {
            FileOutputStream out = new FileOutputStream("grades.xml");

            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("Grades");
            for (Grade grade : gradesList) {

                writer.writeStartElement("Grade");
                writer.writeAttribute("id", Integer.toString(grade.getId()));
                writer.writeStartElement("mark");
                writer.writeCharacters(Integer.toString(grade.getMark()));
                writer.writeEndElement();
                writer.writeStartElement("studentid");
                writer.writeCharacters(Integer.toString(grade.getStudentId()));
                writer.writeEndElement();
                writer.writeStartElement("examid");
                writer.writeCharacters(Integer.toString(grade.getExamId()));
                writer.writeEndElement();
                writer.writeEndElement();



            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();

            writer.close();


        } catch (IOException | XMLStreamException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public ArrayList<Student> getLosers() throws RemoteException {
        ArrayList<Integer> array = new ArrayList<>();
        for(Grade gr: getGrades()){
            if(array.contains(gr.getStudentId())){
                continue;
            }
            if(gr.mark < 4) {
                array.add(gr.getStudentId());
            }
        }
        ArrayList<Student> losers = new ArrayList<>();
        for(int id: array){
            for(Student st: getStudents()){
                if(st.getId() == id){
                    losers.add(st);
                }
            }
        }
        return losers;
    }

    @Override
    public void deleteLosers() throws RemoteException, FileNotFoundException, XMLStreamException {
        ArrayList<Student> losers = getLosers();
        for(Student student : losers){
            removeStudent(student.getId());
        }
        for(Student student : losers){
            for (Grade grade : gradesList){
                if (grade.getStudentId() == student.getId()) {
                    removeGrade(grade.getId());
                }
            }
        }
    }


//    @Override
//    public ArrayList<Student> getLosers() throws SQLException, RemoteException {
//        Statement statement = this.connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from grade where mark < 4;");
//        PreparedStatement preparedStatement = this.connection.prepareStatement("select * from student where id=?;");
//        ArrayList<Integer> array = new ArrayList<>();
//        ArrayList<Student> arr = new ArrayList<>();
//        while(resultSet.next()){
//            int st = resultSet.getInt(3);
//            if(array.contains(st)) {
//                continue;
//            }
//            array.add(st);
//            preparedStatement.setInt(1, st);
//            ResultSet resultSet1 = preparedStatement.executeQuery();
//            while (resultSet1.next()){
//                int id2 = resultSet1.getInt(1);
//                String name = resultSet1.getString(2);
//                String secondName = resultSet1.getString(3);
//                int group = resultSet1.getInt(4);
//                arr.add(new Student(id2, name, secondName, group));
//                System.out.printf("id-%d, firstName-%s secondName-%s, group-%d\n", id2, name, secondName, group);
//            }
//        }
//        return arr;
//    }
//
//    @Override
//    public void deleteLosers() throws SQLException, RemoteException {
//        Statement statement = this.connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from grade where mark < 4;");
//        while(resultSet.next()){
//            PreparedStatement preparedStatement2 = this.connection.prepareStatement("delete from grade where studentid=?;");
//
//            PreparedStatement preparedStatement = this.connection.prepareStatement("delete from student where id=?;");
//            int st = resultSet.getInt(3);
//            preparedStatement2.setInt(1, st);
//            preparedStatement2.executeUpdate();
//            preparedStatement.setInt(1, st);
//            preparedStatement.executeUpdate();
//            System.out.println("Losers were deleted!\n");
//        }
//    }


}
