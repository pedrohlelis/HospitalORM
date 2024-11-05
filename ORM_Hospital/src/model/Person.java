package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	
	public Person(String name, String email, String cpf, String phone) {
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.phone = phone;
	}
	
	public Person() {
		
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
	public String getEmail() {
		return email;
	}
	public String getCpf() {
		return cpf;
	}
	public String getPhone() {
		return phone;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	public void setEmail(String value) {
		this.phone = value;
	}
	public void setCpf(String value) {
		this.phone = value;
	}
	public void setPhone(String value) {
		this.phone = value;
	}
}
