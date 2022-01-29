package ro.unitbv;

import lombok.Data;
import ro.unitbv.datatypes.Course;
import ro.unitbv.datatypes.Student;
import ro.unitbv.datatypes.Teacher;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseHolder {
    private List<Course> courses;

    public CourseHolder() {
        this.courses = new ArrayList<>();
    }

    private Course search(Course course) throws Exception {
        int i = courses.indexOf(course);
        if (i != -1) {
            return courses.get(i);
        } else {
            throw new Exception("Cursul " + course.getName() + " nu se se regaseste in programa scolara");
        }
    }

    public void addCourse(Course c) {
        this.courses.add(c);
    }

    public void removeCourse(Course c) throws Exception {
        if (!this.courses.remove(c)) {
            throw new Exception("Cursrul " + c.getName() + " nu poate fi sters pentru ca nu se regaseste in programa scolara");
        }
    }

    public void reportStudentsOf(Course c) throws Exception {
        Course course = this.search(c);
        System.out.println(course.getAllStudents());
    }

    public void reportAllCourses() {
        for (Course c : courses) {
            System.out.println(c.getName() + " - " + c.getDescription() + " - " + c.getTeacher());
            System.out.println(c.getAllStudents());
        }
    }

    public void reportAllStudentsGrades() {
        for (Course c : courses) {
            System.out.println(c.getName());
            for (Student s : c.getStudents()) {
                String gradeAsString = c.getGrades().get(s) != null ? c.getGrades().get(s).toString() : " nu a fost notat";
                System.out.println(s.getName() + " " + s.getLastName() + " are nota: " + gradeAsString);
            }
        }
    }

    public void reportGradesOf(Course course) throws Exception {
        Course c = this.search(course);
        System.out.println("Media studentilor la cursul " + c.getName() + " este:" + c.studentsGradeAverage());
    }

    public void reportAverageGradesOf(Teacher teacher) {
        float sum = 0;
        int count = 0;
        for (Course c : courses) {
            if (c.getTeacher().equals(teacher)) {
                sum += c.studentsGradeAverage();
                count += 1;
            }
        }
        float average = count == 0 ? 0 : sum / (float) count;
        System.out.println("Mediat notelor date de profesorul: " + teacher + " este: " + average);
    }
}
