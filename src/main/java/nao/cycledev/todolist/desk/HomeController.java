package nao.cycledev.todolist.desk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nao.cycledev.todolist.desk.model.Event;

public class HomeController {
	
	private ObservableList<Event> eventData = FXCollections.observableArrayList();
	
	@FXML 
	private TableView<Event> tvEvents;
	@FXML
    private TableColumn<Event, String> colEventTitle;
	
	public HomeController() {
		eventData.add(new Event(1, "Event 1", "Event 1111", 1));
		eventData.add(new Event(2, "Event 2", "Event 2222", 1));
		eventData.add(new Event(3, "Event 3", "Event 3333", 2));
		eventData.add(new Event(4, "Event 4", "Event 4444", 1));
		eventData.add(new Event(5, "Event 5", "Event 5555", 2));
	}
	
	@FXML
    private void initialize() {
		colEventTitle.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTitle"));
		
		tvEvents.setItems(eventData);
	}

}
