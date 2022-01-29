package ro.unitbv.testdata;

import lombok.Getter;
import ro.unitbv.CourseHolder;
import ro.unitbv.datatypes.Course;
import ro.unitbv.datatypes.Student;
import ro.unitbv.datatypes.Teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HardcodedData {

    @Getter
    private final CourseHolder manager;
    private final Random rand;
    private final int minimumRequiredStudents;
    @Getter
    private final Student[] dataSetOfStudent;
    @Getter
    private final Teacher[] dataSetOfProfesor;

    public HardcodedData() {
        this.manager = new CourseHolder();
        this.rand = new Random();
        this.minimumRequiredStudents = 5;
        this.dataSetOfStudent = createStudentsData();
        this.dataSetOfProfesor = createProfesorData();
        this.createCoursesData();
    }

    private Student[] createStudentsData() {
        String[] nume = {"Jarcau", "Oprea", "Solomon", "Patrascu", "Damian", "Cristea", "Visoiu", "Andrei", "Petreanu", "Dragomir", "Gavrila", "Suciu", "Rotaru", "Grigorescu", "Dudulescu", "Stanculescu"
                , "Vajaiac", "Istudor", "Bruma", "Neagu", "Popa", "Gribincea", "Cucu", "Milea", "Coca", "Iorga", "Budau", "Maracineanu", "Pascociu", "Ionita", "Paltanea"
                , "Spunei", "Stoian", "Raulea", "Trifan", "Visan", "Rusu", "Silitra", "Puia"};
        String[] prenume = {"Stefan", "Ionut", "Andrei", "Nicolae", "Maria", "Florinela", "Andrei", "Mihai", "Marius", "Sergiu", "Iulian", "Mihai", "Daria", "Stefan", "Stefan", "Daniel", "Marius"
                , "Cristian", "Daniel", "Elena", "Ana-Maria", "Valentina", "Ioana", "Daniel", "Paul", "Bianca", "David", "Constantin", "Andreea", "Cezar", "Auras", "Razvan", "Emanuel", "Andrei", "Ioan", "Marian", "Victor", "Florin", "Madalina"};

        Student[] studenti = new Student[nume.length];
        for (int i = 0; i < nume.length; i++) {
            Student s = new Student(nume[i], prenume[i], 1 + rand.nextInt(4));
            studenti[i] = s;
        }
        return studenti;
    }

    private Teacher[] createProfesorData() {
        String[] nume = {"URSUTIU", "PANA", "ALEXANDRU", "CRETU ", "KRISTALY", "DANILA", "DEMETER", "DIACONU", "ILEA", "POP", "BOER"};
        String[] prenume = {"DORU", "GHEORGHE", "MARIAN", "NICOLAE CONSTANTIN", "DOMINIC", "ADRIAN", "ROBERT", "LAURENTIU", "GELU", "MIHAIL", "ATTILA"};
        Teacher[] profesori = new Teacher[nume.length];
        for (int i = 0; i < nume.length; i++) {
            profesori[i] = new Teacher(nume[i], prenume[i]);
        }
        return profesori;
    }

    private Set<Student> createRandomSetOfStudents() {
        int studentInscrisiLaCurs = minimumRequiredStudents + rand.nextInt(dataSetOfStudent.length - minimumRequiredStudents);
        Set<Student> setOfStudents = new HashSet<>();
        for (int i = 0; i < studentInscrisiLaCurs; i++) {
            int randomStudentIndex = rand.nextInt(dataSetOfStudent.length);
            setOfStudents.add(dataSetOfStudent[randomStudentIndex]);
        }
        return setOfStudents;
    }

    private void createCoursesData() {
        String[] curs = {"Teoria sistemelor", "Masurari electronice", "Dispozitive electronice", "Structuri de date", "Procesarea semnalelor", "Limba engleza", "Limbaje formale", "PCLP 1", "PCLP 2"};
        String descriere = "descriere curs";
        ArrayList<Course> cursuri = new ArrayList<>();
        for (String numeCurs : curs) {
            Set<Student> studentsData = createRandomSetOfStudents();
            Teacher profesor = dataSetOfProfesor[rand.nextInt(dataSetOfProfesor.length)];
            Course c = new Course(numeCurs, descriere, profesor, studentsData);
            manager.addCourse(c);
        }
    }

    public void gradeStudents() {
        for (Course c : manager.getCourses()) {
            for (Student s : c.getStudents()) {
                try {
                    c.gradeStudent(s, 1 + rand.nextInt(10));
                } catch (Exception ignored) {

                }
            }
        }
    }
}
