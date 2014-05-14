package nao.cycledev.todolist.desk.datamanager;

import nao.cycledev.todolist.desk.model.Task;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class TaskDataManager extends CouchDbRepositorySupport<Task> {

    public TaskDataManager(CouchDbConnector db) {
        super(Task.class, db);
    }
}
