package nao.cycledev.todolist.desk;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nao.cycledev.todolist.desk.model.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventAddEditDialogController {
	private static final Logger logger = LoggerFactory.getLogger(EventAddEditDialogController.class);
	
	@FXML
	private TextField txtEventTitle;
	
	@FXML
	private TextField txtEventDesc;
	
	private Stage dialogStage;
    //private Event event;
    private boolean okClicked = false;
	
	@FXML
	private void handleOKEvent() {
		logger.debug("handleOKEvent");
		
		okClicked = true;
		dialogStage.close();
	 
	}
	
	@FXML
	private void handleCancelEvent() {
		logger.debug("handleCancelEvent");
		
		dialogStage.close();
	 
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
}
