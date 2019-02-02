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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="questions")
public class Question implements Serializable{

	@Id @GeneratedValue
	private Long id_question;
	@Column
	private Date date_creation_question;
	@Column
	private String question;
	@Column(length=70)
	private String type;
	@Column
	private Integer points;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_quizz", nullable = false)
	@JsonManagedReference
	private Quizz quizz;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "question")
	@JsonBackReference
	private Set<Indice> indices =  new HashSet<Indice>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "question")
	@JsonBackReference
	private Set<Multimedia> medias =  new HashSet<Multimedia>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "question")
	@JsonBackReference
	private Set<Reponse> reponses =  new HashSet<Reponse>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "question")
	@JsonBackReference
	private Set<ReponseEleve> reponsesEleve =  new HashSet<ReponseEleve>();
	
	
	
	
	public Question(Date date_creation_question, String question, String type, Integer points) {
		super();
		this.date_creation_question = date_creation_question;
		this.question = question;
		this.type = type;
		this.points = points;
	}



	public Question(Date date_creation_question, String question, String type, Integer points, Quizz quizz) {
		super();
		this.date_creation_question = date_creation_question;
		this.question = question;
		this.type = type;
		this.points = points;
		this.quizz = quizz;
	}



	public Question(Long id_question, Date date_creation_question, String question, String type, Integer points) {
		super();
		this.id_question = id_question;
		this.date_creation_question = date_creation_question;
		this.question = question;
		this.type = type;
		this.points = points;
	}
	
	



	public Question() {
		super();
	}



	public Long getId_question() {
		return id_question;
	}



	public Date getDate_creation_question() {
		return date_creation_question;
	}



	public String getQuestion() {
		return question;
	}



	public String getType() {
		return type;
	}



	public Integer getPoints() {
		return points;
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
		return quizz;
	}



	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}



	public Set<Indice> getIndices() {
		return indices;
	}



	public void setIndices(Set<Indice> indices) {
		this.indices = indices;
	}



	public Set<Multimedia> getMedias() {
		return medias;
	}



	public void setMedias(Set<Multimedia> medias) {
		this.medias = medias;
	}



	public Set<Reponse> getReponses() {
		return reponses;
	}



	public void setReponses(Set<Reponse> reponses) {
		this.reponses = reponses;
	}



	public Set<ReponseEleve> getReponsesEleve() {
		return reponsesEleve;
	}



	public void setReponsesEleve(Set<ReponseEleve> reponsesEleve) {
		this.reponsesEleve = reponsesEleve;
	}
	
	
	
	
	
}
