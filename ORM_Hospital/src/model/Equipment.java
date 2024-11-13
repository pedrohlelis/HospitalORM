package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;


    @Column(name = "in_use", nullable = false)
    private boolean inUse;


    public Equipment() {
    }


    public Equipment(String name, String type, boolean inUse) {
        this.name = name;
        this.type = type;
        this.inUse = inUse;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setInUse(boolean inUse) {
    	this.inUse = inUse; 
    }
    
    public boolean isInUse() {
        return inUse;
    }


}
