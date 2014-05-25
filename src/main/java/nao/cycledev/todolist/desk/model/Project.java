package nao.cycledev.todolist.desk.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;

import java.util.HashSet;
import java.util.Set;

public class Project extends CouchDbDocument {

    private String projectTitle;
    private String projectDesc;
    private Set<Task> tasks;

    public Project() {
        tasks = new HashSet<>();
    }

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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
