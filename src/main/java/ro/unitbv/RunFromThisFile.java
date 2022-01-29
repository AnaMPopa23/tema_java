package ro.unitbv;


import ro.unitbv.datatypes.Course;
import ro.unitbv.datatypes.User;

import java.util.Scanner;

public class RunFromThisFile {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        System.out.println("> Username:"); String username = scan.nextLine();
        System.out.println("> Password:"); String password = scan.nextLine();

        Application.getInstance().login(username, password);

        if(Application.getInstance().getCurrentUser() == null) {
            return;
        }
        User.UserAccountType accountType = Application.getInstance().getCurrentUser().getAccountType();

        System.out.println(Application.getInstance().getCurrentUser());
        Application.getInstance().getData().reportAllCourses();

        switch (accountType) {
            case STUDENT:
                displayStudentMenu();
            case TEACHER:
                displayTeacherMenu();
        }
    }

    public static void displayStudentMenu() {

    }

    public static void displayTeacherMenu() {

    }
}
