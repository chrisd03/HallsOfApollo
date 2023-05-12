package projects.java.hallsofapollo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigSingleton {
    private static final String configurationFileName = "src/main/resources/projects/java/hallsofapollo/config.json";
    private static ConfigSingleton instance;
    private String metSearchURL;
    private String metObjectURL;
    private String databaseName;

    private ConfigSingleton() {
        setFieldsFromJSON();
    }

    public static ConfigSingleton getInstance() {
        if (instance == null) {
            instance = new ConfigSingleton();
        }
        return instance;
    }

    public String getMetSearchURL() {
        return metSearchURL;
    }
    public String getMetObjectURL() {
        return metObjectURL;
    }

    public String getDatabaseFilename() {
        return databaseName;
    }

    private void setFieldsFromJSON() {
        String jsonText = readJSON(configurationFileName);
        JSONObject root = new JSONObject(jsonText);
        metSearchURL = root.getString("MetSearchAPI");
        metObjectURL = root.getString("MetObjectAPI");
        databaseName = root.getString("database");
    }

    private String readJSON(String fileName){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error: File not found");
        } catch (IOException e) {
            throw new RuntimeException("Error: I/O problem");
        }
    }
}
