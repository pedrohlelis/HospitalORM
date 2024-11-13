package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Room;
import jakarta.persistence.Persistence;

public class RoomRepository {

    private EntityManager em;
	private EntityManagerFactory emf; 
	
	public RoomRepository() {
        this.emf = Persistence.createEntityManagerFactory("hospitalPU");
    }
	
	private EntityManager getEntityManager() {
        if (this.em == null || !this.em.isOpen()) {
            this.em = this.emf.createEntityManager();
        }
        return this.em;
    }
	
	
	public boolean save(Room room) {
        EntityManager em = this.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
        	em.close();
        }
    }
    
    public List<Room> getAllRooms() {
        EntityManager em = this.getEntityManager();
        List<Room> rooms = null;
        try {
            rooms = em.createQuery("SELECT r FROM Room r", Room.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return rooms;
    }
    
    public Room findById(Long id) {
        EntityManager em = this.getEntityManager();
        Room room = null;
        try {
            room = em.find(Room.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	em.close();
        }
        return room;
    }
    
    
    public boolean update(Long id, Room room) {
    	EntityManager em = this.getEntityManager();
    	try {
    		em.getTransaction().begin();
    		Room oldRoom = em.find(Room.class, id);
    		if(oldRoom != null) {
    			oldRoom.setRoomNumber(room.getRoomNumber());
    			oldRoom.setAvailable(room.isAvailable());
    			oldRoom.setCapacity(room.getCapacity());
    			oldRoom.setType(room.getType());
    			em.merge(oldRoom); 		
    		}
    		em.getTransaction().commit();
    		return true;
    	} catch (Exception e) {
    		em.getTransaction().rollback();
    		return false;
    	} finally {
			em.close();
		}
    }
    
    public void delete(Long id) {
    	EntityManager em = this.getEntityManager();
    	try {
    		em.getTransaction().begin();
    		Room room = em.find(Room.class, id);
    		if(room != null) {
    			em.remove(room);
    		}
    		em.getTransaction().commit();
    	} catch (Exception e) {
    		em.getTransaction().rollback();
    	} finally {
			em.close();
		}
    }
    
    public void close() {
        if (this.emf != null && this.emf.isOpen()) {
            this.emf.close();
        }
    }
}