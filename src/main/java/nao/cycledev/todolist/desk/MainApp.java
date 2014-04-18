package nao.cycledev.todolist.desk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
		
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

    	logger.debug("Starting ToDo List application");
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent)loader.load(getClass().getResourceAsStream("/fxml/Home.fxml"));

        Scene scene = new Scene(rootNode, 420, 550);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("ToDo List");
        stage.setScene(scene);
        stage.show();
        logger.debug("ToDo List application loaded and showed");
    }
}
