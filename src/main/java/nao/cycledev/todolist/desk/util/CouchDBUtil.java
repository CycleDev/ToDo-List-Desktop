package nao.cycledev.todolist.desk.util;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import java.net.MalformedURLException;

public class CouchDBUtil {

    private static CouchDbConnector dbConnector;

    private static CouchDbConnector configureCouchDbConnector() throws MalformedURLException {
        HttpClient httpClient = new StdHttpClient.Builder().url("http://127.0.0.1:5984/").build();
    	//HttpClient httpClient = new StdHttpClient.Builder().url("https://cycledev.couchappy.com/").build();
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        dbConnector = dbInstance.createConnector("todolistdb", true);        
        return dbConnector;
    }

    public static CouchDbConnector getCouchDbConnector() throws MalformedURLException {
        if (dbConnector != null) {
            return dbConnector ;
        } else {
            return configureCouchDbConnector();
        }
    }
}
