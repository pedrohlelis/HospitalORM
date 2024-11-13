package repository;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Equipment;

public class EquipmentRepository {

    private EntityManager em;
    private EntityManagerFactory emf;

    public EquipmentRepository() {
        this.emf = Persistence.createEntityManagerFactory("hospitalPU");
    }

    private EntityManager getEntityManager() {
        if (this.em == null || !this.em.isOpen()) {
            this.em = this.emf.createEntityManager();
        }
        return this.em;
    }

    public boolean save(Equipment equipment) {
        EntityManager em = this.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(equipment);
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

    public List<Equipment> getAllEquipments() {
        EntityManager em = this.getEntityManager();
        List<Equipment> equipments = null;
        try {
            equipments = em.createQuery("SELECT e FROM Equipment e", Equipment.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return equipments;
    }

    public Equipment findById(Long id) {
        EntityManager em = this.getEntityManager();
        Equipment equipment = null;
        try {
            equipment = em.find(Equipment.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return equipment;
    }

    public boolean update(Long id, Equipment equipment) {
        EntityManager em = this.getEntityManager();
        try {
            em.getTransaction().begin();
            Equipment oldEquipment = em.find(Equipment.class, id);
            if (oldEquipment != null) {
                oldEquipment.setName(equipment.getName());
                oldEquipment.setType(equipment.getType());
                oldEquipment.setInUse(equipment.isInUse());
                em.merge(oldEquipment);
            }
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

    public void delete(Long id) {
        EntityManager em = this.getEntityManager();
        try {
            em.getTransaction().begin();
            Equipment equipment = em.find(Equipment.class, id);
            if (equipment != null) {
                em.remove(equipment);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
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
