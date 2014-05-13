package nao.cycledev.todolist.desk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import nao.cycledev.todolist.desk.component.ProjectComponent;
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

    private ProjectDataManager projectDataManager;

    private ProjectComponentController projectComponentController;

    public void initialize(URL location, ResourceBundle resources) {

        try {
            projectDataManager = new ProjectDataManager(CouchDBUtil.getCouchDbConnector());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        projectComponentController = (ProjectComponentController)pcProjects.getController();
    }

	@FXML
	private void handleDeleteProject() {
        Project selectedProject = projectComponentController.getSelectedProject();

        if (selectedProject != null) {
            logger.debug("Project is removing");
            projectComponentController.RemoveProject(selectedProject);
        } else {
            logger.debug("No project selected for deleting");
        }
	}

	@FXML
	private void handleEditProject() {
        Project selectedProject = projectComponentController.getSelectedProject();

        if (selectedProject != null) {
            logger.debug("Project is editing");
			//int selectedIndex = tvEvents.getSelectionModel().getSelectedIndex();
            //if (mainApp.showEventDialog(selectedEvent)) {
                //tvEvents.setItems(dataManager.getEvents());
                //tvEvents.getSelectionModel().select(selectedIndex);
            //    logger.debug("Event edited");
 			//}
		} else {
			logger.debug("No project selected for editing");
		}
	}

	@FXML
	private void handleNewProject() {
		Project project = new Project();
		//mainApp.showEventDialog(event);
        //tvEvents.setItems(dataManager.getEvents());
        //tvEvents.getSelectionModel().selectLast();
 	    logger.debug("New project is created");
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
