package projet.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	
	@Id @GeneratedValue
	private Long id_user;
	private String nom_user;
	private String prenom_user;
	private String email_user;
	private String pseudo_user;
	private String password_user;
	
	
	
	
	public User() {
		super();
	}
	public User(String nom_user, String prenom_user, String email_user, String pseudo_user, String password_user) {
		super();
		this.nom_user = nom_user;
		this.prenom_user = prenom_user;
		this.email_user = email_user;
		this.pseudo_user = pseudo_user;
		this.password_user = password_user;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getNom_user() {
		return nom_user;
	}
	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}
	public String getPrenom_user() {
		return prenom_user;
	}
	public void setPrenom_user(String prenom_user) {
		this.prenom_user = prenom_user;
	}
	public String getEmail_user() {
		return email_user;
	}
	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}
	public String getPseudo_user() {
		return pseudo_user;
	}
	public void setPseudo_user(String pseudo_user) {
		this.pseudo_user = pseudo_user;
	}
	public String getPassword_user() {
		return password_user;
	}
	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}
	

}
