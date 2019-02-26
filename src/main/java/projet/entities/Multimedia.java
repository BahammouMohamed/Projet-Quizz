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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="multimedias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_media")
public class Multimedia implements Serializable{
	
	@Id @GeneratedValue
	private Long id_media;
	@Column
	private String path_media;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_creation_media;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_update_media;
	
	@Column(length=50)
	private String type_media; //sons,video,img...
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
	private Question question;
	
	
	
	public Multimedia() {
		super();
	}
	
	public Multimedia(String path_media, String type_media, Question question) {
		super();
		this.path_media = path_media;
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
	
	

	public Date getDate_update_media() {
		return date_update_media;
	}

	public void setDate_update_media(Date date_update_media) {
		this.date_update_media = date_update_media;
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
