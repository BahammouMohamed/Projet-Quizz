package projet.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="questions")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_question")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question implements Serializable{

	@Id @GeneratedValue
	private Long id_question;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_creation_question;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_update_question;
	
	@Column
	private String question;
	@Column(length=70)
	private String type;
	@Column
	private Integer points;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quizz", nullable = false)
	private Quizz quizz;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "question")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<Indice> indices =  new HashSet<Indice>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "question")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<Multimedia> medias =  new HashSet<Multimedia>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "question")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<Reponse> reponses =  new HashSet<Reponse>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "question")
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE) 
	private Set<ReponseEleve> reponsesEleve =  new HashSet<ReponseEleve>();
	
	
	
	
	public Question( String question, String type, Integer points) {
		super();
		this.question = question;
		this.type = type;
		this.points = points;
	}



	public Question( String question, String type, Integer points, Quizz quizz) {
		super();
		this.question = question;
		this.type = type;
		this.points = points;
		this.quizz = quizz;
	}


	public Question() {
		super();
	}



	public Long getId_question() {
		return this.id_question;
	}



	public Date getDate_creation_question() {
		return this.date_creation_question;
	}



	public String getQuestion() {
		return this.question;
	}



	public String getType() {
		return this.type;
	}
	


	public Date getDate_update_question() {
		return date_update_question;
	}



	public void setDate_update_question(Date date_update_question) {
		this.date_update_question = date_update_question;
	}



	public Integer getPoints() {
		return this.points;
	}



	public void setId_question(Long id_question) {
		this.id_question = id_question;
	}



	public void setDate_creation_question(Date date_creation_question) {
		this.date_creation_question = date_creation_question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}



	public void setType(String type) {
		this.type = type;
	}



	public void setPoints(Integer points) {
		this.points = points;
	}



	public Quizz getQuizz() {
		return this.quizz;
	}



	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}



	public Set<Indice> getIndices() {
		return this.indices;
	}



	public void setIndices(Set<Indice> indices) {
		this.indices = indices;
	}



	public Set<Multimedia> getMedias() {
		return this.medias;
	}



	public void setMedias(Set<Multimedia> medias) {
		this.medias = medias;
	}



	public Set<Reponse> getReponses() {
		return this.reponses;
	}



	public void setReponses(Set<Reponse> reponses) {
		this.reponses = reponses;
	}



	public Set<ReponseEleve> getReponsesEleve() {
		return this.reponsesEleve;
	}



	public void setReponsesEleve(Set<ReponseEleve> reponsesEleve) {
		this.reponsesEleve = reponsesEleve;
	}
	
	
	
	
	
}
