package nao.cycledev.todolist.desk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import nao.cycledev.todolist.desk.component.ProjectComponent;
import nao.cycledev.todolist.desk.component.TaskComponent;
import nao.cycledev.todolist.desk.datamanager.ProjectDataManager;
import nao.cycledev.todolist.desk.model.Project;
import nao.cycledev.todolist.desk.util.CouchDBUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends BaseController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private MainApp mainApp;

    @FXML
    private VBox apProjects;

    @FXML
    private ProjectComponent pcProjects;
    
    @FXML 
    private TaskComponent tcTasks;

    private ProjectDataManager projectDataManager;

    private ProjectComponentController projectComponentController;
    
    int i = 0;

    public void initialize(URL location, ResourceBundle resources) {

        try {
            projectDataManager = new ProjectDataManager(CouchDBUtil.getCouchDbConnector());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        projectComponentController = (ProjectComponentController)pcProjects.getController();
        projectComponentController.setTaskComponent(tcTasks);
    }

	@FXML
	private void handleDeleteProject() {
        Project selectedProject = projectComponentController.getSelectedProject();

        if (selectedProject != null) {
            logger.debug("Project " + selectedProject.getProjectTitle() + " is removing");
            projectComponentController.RemoveProject(selectedProject);
        } else {
            logger.debug("No project selected for deleting");
        }
	}

	@FXML
	private void handleEditProject() {
        Project selectedProject = projectComponentController.getSelectedProject();

        if (selectedProject != null) {
            logger.debug("Project " + selectedProject.getProjectTitle() + " is editing");
            projectComponentController.UpdateProject(selectedProject);			
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
		
		//mainApp.showEventDialog(event);
        		
		projectComponentController.CreateProject(project);	
 	    logger.debug("Project " + project.getProjectTitle() + " is created");
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
