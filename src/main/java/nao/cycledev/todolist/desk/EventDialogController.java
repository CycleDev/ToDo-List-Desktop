package nao.cycledev.todolist.desk;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nao.cycledev.todolist.desk.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDialogController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(EventDialogController.class);

    private Stage dialogStage;
    private Project project;
    private boolean okClicked = false;

	@FXML
	private TextField txtEventTitle;
	
	@FXML
	private TextArea txtEventDesc;
	
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;

    }
	
	@FXML
	private void handleOKEvent() {
		
	    logger.debug("handleOKEvent");
        project.setProjectTitle(txtEventTitle.getText().trim());
		project.setProjectDesc(txtEventDesc.getText().trim());

       // dataManager.saveEvent(event);

		okClicked = true;
		dialogStage.close();

	}
	
	@FXML
	private void handleCancelEvent() {
		
		logger.debug("handleCancelEvent");
        okClicked = false;
		dialogStage.close();
	 
	}
	
	public boolean isOkClicked() {

		return okClicked;

	}
	
	public void setProject(Project project) {

		this.project = project;
		txtEventTitle.setText(this.project.getProjectTitle());
		txtEventDesc.setText(this.project.getProjectDesc());

	}
}
