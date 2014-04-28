package nao.cycledev.todolist.desk.datamanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nao.cycledev.todolist.desk.model.Event;
import nao.cycledev.todolist.desk.util.DBUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EventManager {

    private static final Logger logger = LoggerFactory.getLogger(EventManager.class);

    public ObservableList<Event> getEvents() {
        logger.debug("getEvents");
        Session session = DBUtil.getSessionFactory().openSession();
        List events = null;
        try{
            if (session.isConnected()){
                events = session.createCriteria(Event.class).list();
            }
        }catch (HibernateException e) {
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        ObservableList<Event> eventData = FXCollections.observableArrayList();

        for (Object event : events) {
            eventData.add((Event) event);
        }

        return eventData;
    }

    private int addEvent(Event event) {
        Session session = DBUtil.getSessionFactory().openSession();
        Integer eventID = -1;
        Transaction tran = null;
        try{
            tran = session.beginTransaction();
            eventID = (Integer) session.save(event);
            tran.commit();
        }catch (HibernateException e) {
            if(tran != null) {
                tran.rollback();
            }
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
        return eventID;
    }

    private void updateEvent(Event event){
        Session session = DBUtil.getSessionFactory().openSession();
        Transaction tran = null;
        try{
            tran = session.beginTransaction();
            session.update(event);
            tran.commit();
        }catch (HibernateException e) {
            if(tran != null) {
                tran.rollback();
            }
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
    }

    public void saveEvent(Event event){
        if (event.getEventId() > 0){
                updateEvent(event);
        }else{
                addEvent(event);
        }
    }

    public void deleteEvent(Integer eventID){
        Session session = DBUtil.getSessionFactory().openSession();
        Transaction tran = null;
        try{
            tran = session.beginTransaction();
            Event event = (Event)session.get(Event.class, eventID);
            session.delete(event);
            tran.commit();
        }catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
    }
}
