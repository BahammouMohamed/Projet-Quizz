package projet.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Reponse implements Serializable{
	
	@Id @GeneratedValue
	private Long id_reponse;
	@Column
	private String reponse;
	@Column
	private boolean isCorrect;
	@Column
	private Date date_creation_reponse;
	
	
	
	/**
	 * Ici mapper la clé etrangere id_user et id_question 
	 */
	
	public Reponse(String reponse, boolean isCorrect, Date date_creation_reponse) {
		super();
		this.reponse = reponse;
		this.isCorrect = isCorrect;
		this.date_creation_reponse = date_creation_reponse;
	}
	
	public Reponse() {
		super();
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
	
	
	
	

}
