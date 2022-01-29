package ro.unitbv.datatypes;


import lombok.Data;

@Data
public class User {

    public enum UserAccountType {
        STUDENT,
        TEACHER
    }

    public User(String username, String password, UserAccountType accountType, Person person) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.person = person;
    }

    private String username;
    private String password;
    private UserAccountType accountType;
    private Person person;
}
