package projet.entities;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_user")
public class User implements Serializable{
	
	@Id @GeneratedValue
	private Long id_user;
	@Column(length=50)
	private String nom_user;
	@Column(length=50)
	private String prenom_user;
	@Column(length=100)
	private String email_user;
	@Column(length=50)
	private String pseudo_user;
	@Column(length=50)
	private String password_user;
	@Column(length=25)
	private String status;
	@Column
	private boolean validated;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<Quizz> quizzs =  new HashSet<Quizz>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "user" )
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<ReponseEleve> reponses =  new HashSet<ReponseEleve>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<Score> scores =  new HashSet<Score>();
	
	
	
	
	
	public User() {
		super();
	}
	
	public User(String nom_user, String prenom_user, String email_user, String pseudo_user, String password_user,
			String status, boolean validated) {
		super();
		this.nom_user = nom_user;
		this.prenom_user = prenom_user;
		this.email_user = email_user;
		this.pseudo_user = pseudo_user;
		this.password_user = password_user;
		this.status = status;
		this.validated = validated;
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
	public Set<Quizz> getQuizzs() {
		return quizzs;
	}
	public void setQuizzs(Set<Quizz> quizzs) {
		this.quizzs = quizzs;
	}
	public Set<ReponseEleve> getReponses() {
		return reponses;
	}
	public void setReponses(Set<ReponseEleve> reponses) {
		this.reponses = reponses;
	}
	public Set<Score> getScores() {
		return scores;
	}
	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	

}
