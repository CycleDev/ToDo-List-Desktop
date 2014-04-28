package nao.cycledev.todolist.desk.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {

    @Id @GeneratedValue
    @Column(name = "ID")
	private int eventId;

    @Column(name = "EventTitle")
	private String eventTitle;

    @Column(name = "EventDesc")
	private String eventDescription;

    @Column(name = "Status")
	private int eventStatus;

    @Column(name = "Created")
    private Date eventCreated;
	
	public Event() {

        this.eventCreated = new Date();
    }

	public Event(String eventTitle, String eventDescription, int eventStatus) {
		super();
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

    public Date getEventCreated() {
        return eventCreated;
    }

}
