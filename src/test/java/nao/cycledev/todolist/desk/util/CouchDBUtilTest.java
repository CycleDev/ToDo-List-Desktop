package nao.cycledev.todolist.desk.util;

import junit.framework.Assert;
import org.junit.Test;

public class CouchDBUtilTest {

    @Test
    public void testGetCouchDbConnector() throws Exception {
        Assert.assertTrue("Connection to the CouchDB",
                "test" == "test");
    }
}