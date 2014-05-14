package nao.cycledev.todolist.desk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nao.cycledev.todolist.desk.datamanager.ProjectDataManager;
import nao.cycledev.todolist.desk.model.Project;
import nao.cycledev.todolist.desk.util.CouchDBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectComponentController extends BaseController implements Initializable {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectComponentController.class);

    @FXML
    private TableView tvProjects;

    @FXML
    private TableColumn tcProjectName;

    private ProjectDataManager projectDataManager;

    public void initialize(URL location, ResourceBundle resources) {

        tcProjectName.setCellValueFactory(new PropertyValueFactory<Project, String>("projectTitle"));

        try {
            projectDataManager = new ProjectDataManager(CouchDBUtil.getCouchDbConnector());
        } catch (MalformedURLException e) {
        	logger.error(e.getMessage());
        }
        LoadProjects();
    }

    private void LoadProjects() {
        ObservableList<Project> projectData = FXCollections.observableArrayList();

        List<Project> projectList = projectDataManager.getAll();

        for (Object project : projectList) {
            projectData.add((Project) project);
        }

        tvProjects.setItems(projectData);
    }

    public void RemoveProject(Project project) {
        projectDataManager.remove(project);
        LoadProjects();
        tvProjects.getSelectionModel().selectLast();
    }
    
    public void UpdateProject(Project project) {
    	int selectedIndex = tvProjects.getSelectionModel().getSelectedIndex();
    	projectDataManager.update(project);
    	LoadProjects();
    	tvProjects.getSelectionModel().select(selectedIndex);    	
    }
    
    public void CreateProject(Project project) {
        projectDataManager.add(project);
        LoadProjects();
        tvProjects.getSelectionModel().selectLast();
    }

    public Project getSelectedProject() {
        return (Project)tvProjects.getSelectionModel().getSelectedItem();
    }
}
