package ro.unitbv;

import lombok.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Data
public class Settings {

    public enum Load_Type {
        HARDCODED,
        KEYBOARD,
        FILE
    }

    public enum Display_Type {
        CONSOLE,
        FILE,
        GUI
    }

    private final String settingsFileLocation;
    private String studentsPath;
    private String teachersPath;
    private String coursesPath;
    private Load_Type loadType;
    private Display_Type displayType;

    public Settings() {
        this.settingsFileLocation = "src/main/resources/settings.xml";
        try {
            loadSettingsFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadSettingsFromFile() throws IOException {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(settingsFileLocation));
            this.studentsPath = properties.getProperty("STUDENTS_PATH");
            this.teachersPath = properties.getProperty("TEACHERS_PATH");
            this.coursesPath = properties.getProperty("COURSES_PATH");
            this.loadType = Load_Type.valueOf(properties.getProperty("LOAD_TYPE"));
            this.displayType = Display_Type.valueOf(properties.getProperty("DISPLAY_TYPE"));
        } catch (IOException e) {
            properties.setProperty("STUDENTS_PATH", "");
            properties.setProperty("TEACHERS_PATH", "");
            properties.setProperty("COURSES_PATH", "");
            properties.setProperty("LOAD_TYPE", "");
            properties.setProperty("DISPLAY_TYPE", "");
            properties.storeToXML(new FileOutputStream(settingsFileLocation), "");
            e.printStackTrace();
        }
    }

}
