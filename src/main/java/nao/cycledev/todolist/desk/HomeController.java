package nao.cycledev.todolist.desk;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nao.cycledev.todolist.desk.datamanager.ProjectDataManager;
import nao.cycledev.todolist.desk.datamanager.TaskDataManager;
import nao.cycledev.todolist.desk.model.Project;
import nao.cycledev.todolist.desk.model.Task;
import nao.cycledev.todolist.desk.util.CouchDBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController extends BaseController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private MainApp mainApp;

    //<editor-fold desc="Project variables">

    @FXML
    private TableView tvProjects;

    @FXML
    private TableColumn tcProjectName;

    private ProjectDataManager projectDataManager;

    //</editor-fold>

    //<editor-fold desc="Task variables">

    @FXML
    private TableView tvTasks;

    @FXML
    private TableColumn tcTaskTitle;

    private TaskDataManager taskDataManager;

    //</editor-fold>
    
    int i = 0;

    public void initialize(URL location, ResourceBundle resources) {
        tcProjectName.setCellValueFactory(new PropertyValueFactory<Project, String>("projectTitle"));
        tvProjects.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        try {
            projectDataManager = new ProjectDataManager(CouchDBUtil.getCouchDbConnector());
            taskDataManager = new TaskDataManager(CouchDBUtil.getCouchDbConnector());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        tvProjects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Project>() {

            public void changed(ObservableValue<? extends Project> observable, Project oldValue, Project newValue) {
                //showPersonDetails(newValue);
            }
        });

        LoadProjects();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    //<editor-fold desc="Project functionality">

	@FXML
	private void handleDeleteProject() {
        Project selectedProject = getSelectedProject();

        if (selectedProject != null) {
            logger.debug("Project " + selectedProject.getProjectTitle() + " is removing");
            projectDataManager.remove(selectedProject);
        } else {
            logger.debug("No project selected for deleting");
        }
	}

	@FXML
	private void handleEditProject() {
        Project selectedProject = getSelectedProject();

        if (selectedProject != null) {
            logger.debug("Project " + selectedProject.getProjectTitle() + " is editing");
            projectDataManager.update(selectedProject);
		} else {
			logger.debug("No project selected for editing");
		}
	}

	@FXML
	private void handleNewProject() {
		Project project = new Project();
		project.setProjectTitle("Project" + i);
		project.setProjectDesc("Desc" + i);
		i++;
		
        projectDataManager.add(project);
 	    logger.debug("Project " + project.getProjectTitle() + " is created");
	}

    private void LoadProjects() {
        ObservableList<Project> projectData = FXCollections.observableArrayList();

        List<Project> projectList = projectDataManager.getAll();

        for (Object project : projectList) {
            projectData.add((Project) project);
        }

        tvProjects.setItems(projectData);
    }

    private Project getSelectedProject() {

        return (Project)tvProjects.getSelectionModel().getSelectedItem();
    }

    //</editor-fold>
	
    //<editor-fold desc="Task functionality">

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

    private Task getSelectedTask() {
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
    //</editor-fold>
}
