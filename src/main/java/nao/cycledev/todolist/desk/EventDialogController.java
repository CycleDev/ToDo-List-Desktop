package nao.cycledev.todolist.desk;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nao.cycledev.todolist.desk.datamanager.EventManager;
import nao.cycledev.todolist.desk.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDialogController {

    private static final Logger logger = LoggerFactory.getLogger(EventDialogController.class);
    private EventManager dataManager = new EventManager();
    private Stage dialogStage;
    private Event event;
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
		event.setEventTitle(txtEventTitle.getText().trim());
		event.setEventDescription(txtEventDesc.getText().trim());

        dataManager.saveEvent(event);

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
	
	public void setEvent(Event event) {
		this.event = event;
		txtEventTitle.setText(this.event.getEventTitle());
		txtEventDesc.setText(this.event.getEventDescription());
	}
}
