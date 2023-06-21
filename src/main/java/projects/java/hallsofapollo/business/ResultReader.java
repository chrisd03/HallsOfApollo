package projects.java.hallsofapollo.business;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class ResultReader {
    private static ResultReader instance;
    public static ResultReader getInstance() {
        if (instance == null) {
            instance = new ResultReader();
        }
        return instance;
    }
    QueryCaller qc = QueryCaller.getInstance();
    private JSONObject queryResults;
    protected JSONArray searchResults;

    public BufferedImage findImage() throws IOException {
        Random random = new Random();
        getSearchResultIDs();
        sendObjectID(searchResults.getInt(random.nextInt(searchResults.length())));
        qc.performObjectQuery();
        queryResults = qc.getQueryResults();
        String url = getImageURL(queryResults);
        return ImageIO.read(new URL(url));
    }

    private void getSearchResultIDs(){
        queryResults = qc.getQueryResults();
        searchResults = queryResults.getJSONArray("objectIDs");
    }

    private String getImageURL(JSONObject objectJSON){
        String imageURL = objectJSON.getString("primaryImage");
        return imageURL;
    }
    private void sendObjectID(int id){
        qc.setObjectID(id);
    }
}
