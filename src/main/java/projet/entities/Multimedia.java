package projet.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Multimedia implements Serializable{
	
	@Id @GeneratedValue
	private Long id_media;
	@Column
	private String path_media;
	@Column
	private Date date_creation_media;
	@Column(length=50)
	private String type_media; //sons,video,img...
	
	
	
	/**
	 * Ici mapper les clés etrangere id_user et id_question
	 */
	
	public Multimedia() {
		super();
	}
	
	public Multimedia(String path_media, Date date_creation_media, String type_media) {
		super();
		this.path_media = path_media;
		this.date_creation_media = date_creation_media;
		this.type_media = type_media;
	}

	public Long getId_media() {
		return id_media;
	}

	public String getPath_media() {
		return path_media;
	}

	public Date getDate_creation_media() {
		return date_creation_media;
	}

	public String getType_media() {
		return type_media;
	}

	public void setId_media(Long id_media) {
		this.id_media = id_media;
	}

	public void setPath_media(String path_media) {
		this.path_media = path_media;
	}

	public void setDate_creation_media(Date date_creation_media) {
		this.date_creation_media = date_creation_media;
	}

	public void setType_media(String type_media) {
		this.type_media = type_media;
	}
	
	



	
	
	
	
	
	
	
	
	
	
}
