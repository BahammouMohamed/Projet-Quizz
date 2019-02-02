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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="indices")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_indice")
public class Indice implements Serializable{
	@Id @GeneratedValue
	private Long id_indice;
	@Column
	private String indice;
	@Column
	private Date date_creation_indice;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_question", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Question question;
	
	
	
	
	public Indice(String indice, Date date_creation_indice) {
		super();
		this.indice = indice;
		this.date_creation_indice = date_creation_indice;
	}

	public Indice() {
		super();
	}
	

	

	public Indice(String indice, Date date_creation_indice, Question question) {
		super();
		this.indice = indice;
		this.date_creation_indice = date_creation_indice;
		this.question = question;
	}

	public Long getId_indice() {
		return id_indice;
	}

	public String getIndice() {
		return indice;
	}

	public Date getDate_creation_indice() {
		return date_creation_indice;
	}

	public void setId_indice(Long id_indice) {
		this.id_indice = id_indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

	public void setDate_creation_indice(Date date_creation_indice) {
		this.date_creation_indice = date_creation_indice;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
	
	
	
	
	
	
	
	
}
