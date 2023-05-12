package projects.java.hallsofapollo.business;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

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
        getSearchResultIDs();
        sendObjectID(searchResults.getInt(0));
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
