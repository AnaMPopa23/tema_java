package ro.unitbv.datatypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends Person {
    private Integer group;

    public Student(String name, String lastName, Integer group) {
        super(name, lastName);
        this.group = group;
    }

    @Override
    public String toString() {
        return "%name% %lastName% (%group%)"
                .replace("%name%", getName())
                .replace("%lastName%", getLastName())
                .replace("%group%", String.valueOf(this.group));
    }
}
