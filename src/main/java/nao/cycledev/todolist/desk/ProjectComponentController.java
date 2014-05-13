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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectComponentController implements Initializable {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectComponentController.class);
	
    @FXML
    TableView tvProjects;

    @FXML
    TableColumn tcProjectName;

    ProjectDataManager projectDataManager;

    public void initialize(URL location, ResourceBundle resources) {

        tcProjectName.setCellValueFactory(new PropertyValueFactory<Project, String>("projectTitle"));

       try {
            projectDataManager = new ProjectDataManager(CouchDBUtil.getCouchDbConnector());
        } catch (MalformedURLException e) {
        	logger.error(e.getMessage());
        }
        LoadProjects();
    }

    public void LoadProjects() {
        ObservableList<Project> projectData = FXCollections.observableArrayList();

        List<Project> projectList = projectDataManager.getAll();

        for (Object project : projectList) {
            projectData.add((Project) project);
        }

        tvProjects.setItems(projectData);
    }
}
