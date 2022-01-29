package ro.unitbv;


import lombok.Data;
import ro.unitbv.console.ConsoleHandler;
import ro.unitbv.datatypes.*;
import ro.unitbv.file.FileHandler;
import ro.unitbv.gui.GUIHandler;
import ro.unitbv.testdata.HardcodedData;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Application {

    private static Application single_instance = null;

    static Application getInstance() {
        if (single_instance == null) {
            single_instance = new Application();
        }
        return single_instance;
    }

    private List<User> users;

    private User currentUser = null;
    private final Settings settings;
    private CourseHolder data;
    private Handler handler;

    public Application() {
        this.settings = new Settings();
        this.users = new ArrayList<>();

        switch (settings.getLoadType()) {
            case FILE:
                this.initUsers();
                break;
            case HARDCODED:
                HardcodedData data = new HardcodedData();
                data.gradeStudents();
                this.data = data.getManager();
                Random r = new Random();

                Student[] students = data.getDataSetOfStudent();
                Teacher[] teachers = data.getDataSetOfProfesor();

                users.add(new User("username", "password", User.UserAccountType.STUDENT, students[r.nextInt(students.length)]));
                users.add(new User("username2", "password", User.UserAccountType.STUDENT, students[r.nextInt(students.length)]));
                users.add(new User("username3", "password", User.UserAccountType.STUDENT, students[r.nextInt(students.length)]));
                users.add(new User("username4", "password", User.UserAccountType.STUDENT, students[r.nextInt(students.length)]));
                users.add(new User("username6", "password", User.UserAccountType.TEACHER, teachers[r.nextInt(teachers.length)]));
            case KEYBOARD:
                break;
            default:
                System.exit(0);
        }

        switch (settings.getDisplayType()) {
            case FILE:
                this.handler = new FileHandler();
                break;
            case CONSOLE:
                this.handler = new ConsoleHandler();
                break;
            case GUI:
                this.handler = new GUIHandler();
                break;
            default:
                System.exit(0);
        }
    }

    private void initUsers() {
        try (FileInputStream fis = new FileInputStream("users.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            this.users = (ArrayList<User>) decoder.readObject();
            decoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(String username, String password) throws Exception {
        for(User u : users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                return;
            }
        }
        throw new Exception("Username sau parola este gresita!");
    }
}
