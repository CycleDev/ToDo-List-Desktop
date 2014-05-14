package nao.cycledev.todolist.desk.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;

public class Task extends CouchDbDocument{

    private int projectId;
	private String taskTitle;
	private String taskDesc;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
	
	@JsonProperty("task_title")
	public String getTaskTitle() {
		return taskTitle;
	}

	@JsonProperty("task_title")
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	@JsonProperty("task_desc")
	public String getTaskDesc() {
		return taskDesc;
	}

	@JsonProperty("task_desc")
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
}
