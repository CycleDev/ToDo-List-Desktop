package nao.cycledev.todolist.desk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nao.cycledev.todolist.desk.datamanager.TaskDataManager;
import nao.cycledev.todolist.desk.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TaskComponentController extends BaseController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(TaskComponentController.class);

    @FXML
    private TableView tvTasks;

    @FXML
    private TableColumn tcTaskTitle;

    private TaskDataManager taskDataManager;

	 public void initialize(URL location, ResourceBundle resources) {
		 
	 }

    int i = 0;

    @FXML
    private void handleDeleteTask() {
        Task selectedTask = getSelectedTask();

        if (selectedTask != null) {
            logger.debug("Task " + selectedTask.getTaskTitle() + " is removing");
            taskDataManager.remove(selectedTask);
            LoadTasks();
            tvTasks.getSelectionModel().selectLast();
        } else {
            logger.debug("No task selected for removing");
        }
    }

    @FXML
    private void handleEditTask() {
        Task selectedTask = getSelectedTask();

        if (selectedTask != null) {
            logger.debug("Task " + selectedTask.getTaskTitle() + " is editing");
            taskDataManager.update(selectedTask);
            LoadTasks();
            tvTasks.getSelectionModel().selectLast();
        } else {
            logger.debug("No task selected for editing");
        }
    }

    @FXML
    private void handleNewTask() {
        Task task = new Task();
        task.setTaskTitle("Task" + i);
        task.setTaskDesc("Desc" + i);
        i++;

        taskDataManager.add(task);
        logger.debug("Task " + task.getTaskTitle() + " is created");
    }

    public Task getSelectedTask() {
        return (Task)tvTasks.getSelectionModel().getSelectedItem();
    }

    private void LoadTasks() {
        ObservableList<Task> taskData = FXCollections.observableArrayList();

        List<Task> taskList = taskDataManager.getAll();

        for (Object task : taskList) {
            taskData.add((Task)task);
        }

        tvTasks.setItems(taskData);
    }
}
