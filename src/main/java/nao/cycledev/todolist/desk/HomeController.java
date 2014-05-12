package nao.cycledev.todolist.desk;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import nao.cycledev.todolist.desk.component.ProjectComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private MainApp mainApp;

    @FXML
    VBox apProjects;

    @FXML
    ProjectComponent pcProjects;

	@FXML
	private void handleDeleteEvent() {
		//Event selectedEvent = tvEvents.getSelectionModel().getSelectedItem();
		//if (selectedEvent != null) {
            //dataManager.deleteEvent(selectedEvent.getEventId());
            //tvEvents.setItems(dataManager.getEvents());
            //tvEvents.getSelectionModel().selectLast();
        //    logger.debug("Event deleted");
		//} else {
			logger.debug("No event selected for deleting");
		//}
	}

	@FXML
	private void handleEditEvent() {
		//Event selectedEvent = tvEvents.getSelectionModel().getSelectedItem();
		//if (selectedEvent != null) {
			//logger.debug("Edit event: " + selectedEvent.getEventId() + " - " + selectedEvent.getEventTitle());
			//int selectedIndex = tvEvents.getSelectionModel().getSelectedIndex();
            //if (mainApp.showEventDialog(selectedEvent)) {
                //tvEvents.setItems(dataManager.getEvents());
                //tvEvents.getSelectionModel().select(selectedIndex);
            //    logger.debug("Event edited");
 			//}
		//} else {
			logger.debug("No event selected for editing");
		//}
	}

	@FXML
	private void handleNewEvent() {
		//Event event = new Event();
		//mainApp.showEventDialog(event);
        //tvEvents.setItems(dataManager.getEvents());
        //tvEvents.getSelectionModel().selectLast();
 	    logger.debug("Created new event:");
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
