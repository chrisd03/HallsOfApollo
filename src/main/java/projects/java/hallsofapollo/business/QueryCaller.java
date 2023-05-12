package projects.java.hallsofapollo.business;

import org.json.JSONObject;
import projects.java.hallsofapollo.data.APIReader;

public class QueryCaller {
    private static QueryCaller instance;
    public static QueryCaller getInstance() {
        if (instance == null) {
            instance = new QueryCaller();
        }
        return instance;
    }
    private JSONObject queryResults;
    private String search;
    private int objectID = 123;
    private APIReader apiReader = new APIReader();

    public void setSearch(String search) {
        this.search = search;
    }
    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }
    public JSONObject getQueryResults() {
        return queryResults;
    }
    public void performSearchQuery(){
        queryResults = apiReader.queryAPI("search", search);
    }
    protected void performObjectQuery(){
        queryResults = apiReader.queryAPI("object", objectID + "");
    }
}
