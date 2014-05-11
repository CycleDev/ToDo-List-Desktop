package nao.cycledev.todolist.desk.datamanager;

import nao.cycledev.todolist.desk.model.Project;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class ProjectDataManager extends CouchDbRepositorySupport<Project> {

    public ProjectDataManager(CouchDbConnector db) {
        super(Project.class, db);
    }
}
