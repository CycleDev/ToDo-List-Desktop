package nao.cycledev.todolist.desk.component;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import nao.cycledev.todolist.desk.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BaseComponent extends Pane {

    static final Logger logger = LoggerFactory.getLogger(ProjectComponent.class);
    private final String resourcePath = "/fxml/%s";

    private BaseController controller;

    public BaseController getController() {
        return controller;
    }

    public BaseComponent() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader loader = new
                FXMLLoader(getClass().getResource(String.format(resourcePath, getFXMLFilePath())));

        try {
            Node root = (Node)loader.load();
            controller = loader.getController();
            this.getChildren().add(root);
            logger.debug("Component " + getFXMLFilePath() + " is loaded");
        }
        catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    protected String getFXMLFilePath() {
        return "";
    }

    @Override
    protected void layoutChildren() {
        for (Node node : getChildren()) {
            layoutInArea(node, 3, 3, getWidth()-6, getHeight()-6, 0, HPos.LEFT, VPos.TOP);
        }
    }
}
