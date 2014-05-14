package nao.cycledev.todolist.desk.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;

public class Project extends CouchDbDocument {

    private String projectTitle;
    private String projectDesc;

    @JsonProperty("project_title")
    public String getProjectTitle() {
        return projectTitle;
    }

    @JsonProperty("project_title")
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    @JsonProperty("project_desc")
    public String getProjectDesc() {
        return projectDesc;
    }

    @JsonProperty("project_desc")
    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

/*    @DocumentReferences(backReference = "projectId", fetch = FetchType.LAZY)
    Set<Task> tasks;

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }*/
}
