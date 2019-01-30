package projet.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReponseEleve implements Serializable{
	
	@Id @GeneratedValue
	private Long id_reponse_eleve;
	@Column
	private String reponse_eleve;
	
	
	/**
	 * Ici mapper les clés etranger id_user et id_question
	 */
	public ReponseEleve(String reponse_eleve) {
		super();
		this.reponse_eleve = reponse_eleve;
	}


	public ReponseEleve() {
		super();
	}


	public Long getId_reponse_eleve() {
		return id_reponse_eleve;
	}


	public String getReponse_eleve() {
		return reponse_eleve;
	}

	public void setId_reponse_eleve(Long id_reponse_eleve) {
		this.id_reponse_eleve = id_reponse_eleve;
	}

	public void setReponse_eleve(String reponse_eleve) {
		this.reponse_eleve = reponse_eleve;
	}
	
	
	

}
