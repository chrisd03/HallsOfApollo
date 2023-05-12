package projects.java.hallsofapollo.data;

import org.json.JSONObject;
import projects.java.hallsofapollo.ConfigSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class APIReader {
    private static ConfigSingleton config = ConfigSingleton.getInstance();
    private static String metSearchAPIQuery = config.getMetSearchURL();
    private static String metObjectAPIQuery = config.getMetObjectURL();
    public static void main(String[] args){
    }
    private static String getJSONText(String url){
        try{
            URL address = new URL(url);
            InputStream inputStream = address.openStream();
            InputStreamReader apiReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(apiReader);
            String parsedText = br.lines().collect(Collectors.joining());
            return parsedText;
        }catch (IOException e){
            throw new RuntimeException("IOException in getJSONText");
        }
    }

    public JSONObject queryAPI(String type, String query){
        String JSONText = "";
        if(type.equals("search")){
            metSearchAPIQuery += query;
            JSONText = getJSONText(metSearchAPIQuery);
        }else if(type.equals("object")){
            metObjectAPIQuery += query;
            JSONText = getJSONText(metObjectAPIQuery);
        }
        JSONObject queryResult = new JSONObject(JSONText);
        return queryResult;
    }
}
