package projet.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="indices")
public class Indice implements Serializable{
	@Id @GeneratedValue
	private Long id_indice;
	@Column
	private String indice;
	@Column
	private Date date_creation_indice;
	
	/**
	 * Ici mapper les clés etrangers id_question et id_user
	 */
	
	public Indice(String indice, Date date_creation_indice) {
		super();
		this.indice = indice;
		this.date_creation_indice = date_creation_indice;
	}

	public Indice() {
		super();
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
	
	
	
	
	
	
	
	
}
