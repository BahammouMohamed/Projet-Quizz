package projet.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quizz  implements Serializable{

	@Id @GeneratedValue
	private Long id_quizz;
	@Column
	private String niveau;
	@Column
	private String matiere;
	@Column
	private String periode;
	@Column
	private Date date_creation_quizz;
	
	/**
	 * 
	 * Ici mapper la clé id_user
	 */
	
	
	public Quizz(String niveau, String matiere, String periode, Date date_creation_quizz) {
		super();
		this.niveau = niveau;
		this.matiere = matiere;
		this.periode = periode;
		this.date_creation_quizz = date_creation_quizz;
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
	
	
	
	
	
}
