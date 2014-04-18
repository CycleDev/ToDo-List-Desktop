package nao.cycledev.todolist.desk.model;

public class Event {
	
	private int eventId;	
	private String eventTitle;	
	private String eventDescription;	
	private int eventStatus;
	
	public Event() { }

	public Event(int eventId, String eventTitle, String eventDescription, int eventStatus) {
		super();
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.eventDescription = eventDescription;
		this.eventStatus = eventStatus;
	}
	
	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public int getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
	}

}
