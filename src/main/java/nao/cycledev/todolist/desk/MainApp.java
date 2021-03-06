package nao.cycledev.todolist.desk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nao.cycledev.todolist.desk.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainApp extends Application {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
    private Stage primaryStage;
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
    public void start(Stage stage) throws Exception {

    	logger.debug("Starting ToDo List application");
        FXMLLoader loader = new FXMLLoader();
    	logger.debug("Load Home window");
        Parent rootNode = (Parent)loader.load(getClass().getResourceAsStream("/fxml/Home.fxml"));
        primaryStage = stage;
        Scene scene = new Scene(rootNode, 800, 600);

        HomeController controller = loader.getController();
        controller.setMainApp(this);

        scene.getStylesheets().add("/styles/style-light.css");
        stage.setTitle("ToDo List");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/cubes_grey_32.png"));
        stage.show();
        logger.debug("ToDo List application loaded and showed");
    }
    
	public boolean showProjectDialog(Project project) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader();
			AnchorPane page = (AnchorPane)loader.load(getClass().getResourceAsStream("/fxml/EventDialog.fxml"));
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Project");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
            //scene.getStylesheets().add("/styles/styles.css");
			dialogStage.getIcons().add(new Image("/images/cubes_grey_32.png"));
			dialogStage.setScene(scene);

            // Set the event into the controller
			EventDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProject(project);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();

		} catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            logger.error(e.getMessage());
            return false;
        }
    }
}
