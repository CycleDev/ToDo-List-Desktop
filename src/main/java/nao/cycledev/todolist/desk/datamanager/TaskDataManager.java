package nao.cycledev.todolist.desk.datamanager;

import nao.cycledev.todolist.desk.model.Task;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;

import java.util.List;

public class TaskDataManager extends CouchDbRepositorySupport<Task> {

    public TaskDataManager(CouchDbConnector db) {
        super(Task.class, db);
    }

    public List<Task> findByProject(String projectId) {
        ViewQuery q = new ViewQuery()
                .allDocs()
                .includeDocs(true).queryParam();

        List<Task> bulkLoaded = db.queryView(q, Task.class);
        return bulkLoaded;
    }
}
