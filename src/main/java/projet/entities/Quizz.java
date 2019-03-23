package projet.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="quizzs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quizz  implements Serializable{

	@Id @GeneratedValue
	private Long id_quizz;
	@Column
	private String niveau;
	@Column
	private String matiere;
	@Column
	private String periode;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_creation_quizz;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_update_quizz;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "quizz")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<Question> questions =  new HashSet<Question>();
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
	
	public Quizz(String niveau, String matiere, String periode, User user) {
		super();
		this.niveau = niveau;
		this.matiere = matiere;
		this.periode = periode;
		this.user = user;
	}

	public Quizz() {
		super();
	}

	public Long getId_quizz() {
		return id_quizz;
	}
	public String getNiveau() {
		return niveau;
	}
	public String getMatiere() {
		return matiere;
	}
	public String getPeriode() {
		return periode;
	}
	public Date getDate_creation_quizz() {
		return date_creation_quizz;
	}
	public void setId_quizz(Long id_quizz) {
		this.id_quizz = id_quizz;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	public void setDate_creation_quizz(Date date_creation_quizz) {
		this.date_creation_quizz = date_creation_quizz;
	}
	
	public Date getDate_update_quizz() {
		return date_update_quizz;
	}

	public void setDate_update_quizz(Date date_update_quizz) {
		this.date_update_quizz = date_update_quizz;
	}
	public Set<Question> getQuestions() {
		return  questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
