package nao.cycledev.todolist.desk;

import nao.cycledev.todolist.desk.datamanager.EventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nao.cycledev.todolist.desk.model.Event;

public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private ObservableList<Event> eventData = FXCollections.observableArrayList();	
	private MainApp mainApp;
    private EventManager dataManager;


	@FXML
	private TableView<Event> tvEvents;
	@FXML
	private TableColumn<Event, String> colEventTitle;

	public HomeController() {
		logger.debug("Create HomeController");
		/*eventData.add(new Event(1, "Event 1", "Event 1111", 1));
		eventData.add(new Event(2, "Event 2", "Event 2222", 1));
		eventData.add(new Event(3, "Event 3", "Event 3333", 2));
		eventData.add(new Event(4, "Event 4", "Event 4444", 1));
		eventData.add(new Event(5, "Event 5", "Event 5555", 2));*/
	}

	@FXML
	private void initialize() {
		logger.debug("Initialize HomeController");
        dataManager = new EventManager();
		colEventTitle.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTitle"));
		tvEvents.setItems(dataManager.getEvents());
	}

	@FXML
	private void handleDeleteEvent() {
		int selectedIndex = tvEvents.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			logger.debug("Deleting event: " + selectedIndex);
			tvEvents.getItems().remove(selectedIndex);
            dataManager.deleteEvent(selectedIndex);
		} else {
			logger.debug("No event selected");
		}
	}

	@FXML
	private void handleEditEvent() {
		Event selectedEvent = tvEvents.getSelectionModel().getSelectedItem();
		if (selectedEvent != null) {
			logger.debug("Edit event: " + selectedEvent.getEventId() + " - " + selectedEvent.getEventTitle());
			if (mainApp.showEventDialog(selectedEvent)) {
                tvEvents.setItems(dataManager.getEvents());
                //tvEvents.getSelectionModel().setSelectedIndex(selectedEvent.getEventId());
			}
				
		} else {
			logger.debug("No event selected for editing");
		}
	}

	@FXML
	private void handleNewEvent() {
		Event event = new Event();		
		mainApp.showEventDialog(event);
        tvEvents.setItems(dataManager.getEvents());
 	    logger.debug("Created new event:");
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
