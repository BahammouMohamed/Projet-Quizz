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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_user")
public class User implements Serializable{
	
	@Id @GeneratedValue
	private Long id_user;
	@Column(length=50)
	private String nom;
	@Column(length=50)
	private String prenom;
	@NotNull
	@Column(unique=true , length=100)
	private String email;
	@NotNull
	@Column(unique=true ,length=50)
	private String pseudo;
	@Column
	private String password;
	@Column(length=25)
	private String status;
	@Column
	private boolean validated;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<Quizz> quizzs =  new HashSet<Quizz>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user" )
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<ReponseEleve> reponses =  new HashSet<ReponseEleve>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<Score> scores =  new HashSet<Score>();
	
	
	
	
	
	public User() {
		super();
	}
	
	public User(String nom_user, String prenom_user, String email_user, String pseudo_user, String password_user,
			String status, boolean validated) {
		super();
		this.nom = nom_user;
		this.prenom = prenom_user;
		this.email = email_user;
		this.pseudo = pseudo_user;
		this.password = password_user;
		this.status = status;
		this.validated = validated;
	}

	public Long getId() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
