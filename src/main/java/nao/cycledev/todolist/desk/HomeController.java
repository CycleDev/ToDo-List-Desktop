package nao.cycledev.todolist.desk;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nao.cycledev.todolist.desk.datamanager.ProjectDataManager;
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

    private Project selectedProject;

    //</editor-fold>
    
    int i = 0;

    public void initialize(URL location, ResourceBundle resources) {
        tcProjectName.setCellValueFactory(new PropertyValueFactory<Project, String>("projectTitle"));

        tcTaskTitle.setCellValueFactory(new PropertyValueFactory<Project, String>("taskTitle"));

        try {
            projectDataManager = new ProjectDataManager(CouchDBUtil.getCouchDbConnector());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        tvProjects.getSelectionModel().selectedItemProperty().addListener((ChangeListener)(observable, oldValue, newValue) -> {
                selectedProject = (Project)newValue;
                LoadTasks();
        });

        LoadProjects();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    //<editor-fold desc="Project functionality">

    @FXML
    private void handleNewProject() {

        logger.debug("Add project");
        Project project = new Project();
        if (mainApp.showProjectDialog(project))
        {
            projectDataManager.add(project);
            logger.debug("Project " + project.getProjectTitle() + " is created");
        }

    }

	@FXML
	private void handleEditProject() {

        logger.debug("Edit project");
        if (selectedProject != null) {
            if (mainApp.showProjectDialog(selectedProject)){
                projectDataManager.update(selectedProject);
                logger.debug("Project " + selectedProject.getProjectTitle() + " is edited");
            }
		} else {
			logger.debug("No project selected for editing");
		}

	}

    @FXML
    private void handleDeleteProject() {

        logger.debug("Remove project");
        if (selectedProject != null) {
            logger.debug("Project " + selectedProject.getProjectTitle() + " is removed");
            projectDataManager.remove(selectedProject);
        } else {
            logger.debug("No project selected for deleting");
        }

    }

    private void LoadProjects() {
        ObservableList<Project> projectData = FXCollections.observableArrayList();

        List<Project> projectList = projectDataManager.getAll();

        for (Object project : projectList) {
            projectData.add((Project) project);
        }

        tvProjects.setItems(projectData);
    }

    //</editor-fold>
	
    //<editor-fold desc="Task functionality">

    @FXML
    private void handleDeleteTask() {
        Task selectedTask = getSelectedTask();

        if (selectedTask != null) {
            logger.debug("Task " + selectedTask.getTaskTitle() + " is removing");
            //taskDataManager.remove(selectedTask);
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
            //taskDataManager.update(selectedTask);
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

        //taskDataManager.add(task);
        logger.debug("Task " + task.getTaskTitle() + " is created");
    }

    private Task getSelectedTask() {
        return (Task)tvTasks.getSelectionModel().getSelectedItem();
    }

    private void LoadTasks() {
        ObservableList<Task> taskData = FXCollections.observableArrayList();

        for (Object task : selectedProject.getTasks()) {
            taskData.add((Task)task);
        }

        tvTasks.setItems(taskData);
    }
    //</editor-fold>
}
