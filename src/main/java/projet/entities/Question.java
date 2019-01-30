package projet.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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
	
	/**
	 * Ici mapper la clé etranger id_question
	 */
	
	
	public Question(Date date_creation_question, String question, String type, Integer points) {
		super();
		this.date_creation_question = date_creation_question;
		this.question = question;
		this.type = type;
		this.points = points;
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
	
	
	
	
	
}
