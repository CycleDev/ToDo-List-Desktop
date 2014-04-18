package nao.cycledev.todolist.desk;

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
	
	@FXML 
	private TableView<Event> tvEvents;
	@FXML
    private TableColumn<Event, String> colEventTitle;
	
	public HomeController() {
		logger.debug("Create HomeController");
		eventData.add(new Event(1, "Event 1", "Event 1111", 1));
		eventData.add(new Event(2, "Event 2", "Event 2222", 1));
		eventData.add(new Event(3, "Event 3", "Event 3333", 2));
		eventData.add(new Event(4, "Event 4", "Event 4444", 1));
		eventData.add(new Event(5, "Event 5", "Event 5555", 2));
	}
	
	@FXML
    private void initialize() {
		logger.debug("Initialize HomeController");
		colEventTitle.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTitle"));
		
		tvEvents.setItems(eventData);
	}
	
	@FXML
	private void handleDeleteEvent() {
	  int selectedIndex = tvEvents.getSelectionModel().getSelectedIndex();
	  if (selectedIndex >= 0) {
		  tvEvents.getItems().remove(selectedIndex);
	  } else {
		  logger.debug("No event selected");	   
	  }
	}

}
