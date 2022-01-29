package ro.unitbv.datatypes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Teacher extends ro.unitbv.datatypes.Person {

    public Teacher(String name, String lastName) {
        super(name, lastName);
    }

    @Override
    public String toString() {
        return "%name% %lastName%"
                .replace("%name%", getName())
                .replace("%lastName%", getLastName());
    }
}
