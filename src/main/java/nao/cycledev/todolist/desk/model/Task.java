package nao.cycledev.todolist.desk.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;

import java.util.Date;

public class Task extends CouchDbDocument{

    public enum TaskPriority {LOW, NORMAL, HIGH};

	private String taskTitle;
	private String taskDesc;
    private TaskPriority taskPriority;
    private boolean taskStatus;
    private Date taskExpirationDate;

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

    @JsonProperty("task_priority")
    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    @JsonProperty("task_priority")
    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    @JsonProperty("task_expdate")
    public Date getTaskExpirationDate() {
        return taskExpirationDate;
    }

    @JsonProperty("task_expdate")
    public void setTaskExpirationDate(Date taskExpirationDate) {
        this.taskExpirationDate = taskExpirationDate;
    }

    @JsonProperty("task_status")
    public boolean getTaskStatus() {
        return taskStatus;
    }

    @JsonProperty("task_status")
    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }
}
