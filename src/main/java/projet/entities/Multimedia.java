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

@Entity
@Table(name="multimedias")
public class Multimedia implements Serializable{
	
	@Id @GeneratedValue
	private Long id_media;
	@Column
	private String path_media;
	@Column
	private Date date_creation_media;
	@Column(length=50)
	private String type_media; //sons,video,img...
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_question", nullable = false)
    private Question question;
	
	
	
	public Multimedia() {
		super();
	}
	
	public Multimedia(String path_media, Date date_creation_media, String type_media) {
		super();
		this.path_media = path_media;
		this.date_creation_media = date_creation_media;
		this.type_media = type_media;
	}
	
	

	public Multimedia(String path_media, Date date_creation_media, String type_media, Question question) {
		super();
		this.path_media = path_media;
		this.date_creation_media = date_creation_media;
		this.type_media = type_media;
		this.question = question;
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
	
	



	
	
	
	
	
	
	
	
	
	
}
