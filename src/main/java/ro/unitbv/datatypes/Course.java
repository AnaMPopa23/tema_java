package ro.unitbv.datatypes;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Data
public class Course {
    private String name;
    private String description;
    private Teacher teacher;
    private Set<Student> students;
    private HashMap<Student, Integer> grades;

    public Course(String name, String description, Teacher teacher, Set<Student> students) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = students;
        this.grades = new HashMap<>();
    }

    public Course(String name) {
        this.name = name;
        this.students = new HashSet<>();
        this.grades = new HashMap<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) throws Exception {
        if(!students.remove(student)) {
            throw new Exception("Studentul " + student + " nu este inscris la acest curs");
        }
        grades.remove(student);
    }

    public void gradeStudent(Student student, int grade) throws Exception {
        if (this.students.contains(student)) {
            this.grades.put(student, grade);
        } else {
            throw new Exception("Studentul " + student + " nu poate fi notat pentru ca nu participa la curs");
        }
    }

    public String getAllStudents() {
        StringBuilder str = new StringBuilder();
        for (Student s : students) {
            str.append(s).append(" -> ").append(grades.get(s)).append("\n");
        }
        return str.toString();
    }

    public float studentsGradeAverage() {
        return (float) grades.values().stream().mapToInt(a -> a).average().orElse(0);
    }
}
