package nao.cycledev.todolist.desk.component;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BaseComponent extends Pane {

    static final Logger logger = LoggerFactory.getLogger(ProjectComponent.class);
    private final String resourcePath = "/fxml/%s.fxml";

    public BaseComponent() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader loader = new
                FXMLLoader(getClass().getResource(String.format(resourcePath, getClass().getSimpleName().toLowerCase())));

        try {
            Node root = (Node)loader.load();
            this.getChildren().add(root);
        }
        catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void layoutChildren() {
        for (Node node : getChildren()) {
            layoutInArea(node, 3, 3, getWidth()-6, getHeight()-6, 0, HPos.LEFT, VPos.TOP);
        }
    }
}
