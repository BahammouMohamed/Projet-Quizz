package projet.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name="reponses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reponse implements Serializable{
	
	@Id @GeneratedValue
	private Long id_reponse;
	@Column
	private String reponse;
	@Column
	private boolean isCorrect;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_creation_reponse;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_update_reponse;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
	private Question question;
	
	
	public Reponse() {
		super();
	}
	
	public Reponse(String reponse, boolean isCorrect, Question question) {
		super();
		this.reponse = reponse;
		this.isCorrect = isCorrect;
		this.question = question;
	}

	public Long getId_reponse() {
		return id_reponse;
	}

	public String getReponse() {
		return reponse;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public Date getDate_creation_reponse() {
		return date_creation_reponse;
	}

	public void setId_reponse(Long id_reponse) {
		this.id_reponse = id_reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public void setDate_creation_reponse(Date date_creation_reponse) {
		this.date_creation_reponse = date_creation_reponse;
	}
	
	public Date getDate_update_reponse() {
		return date_update_reponse;
	}

	public void setDate_update_reponse(Date date_update_reponse) {
		this.date_update_reponse = date_update_reponse;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
