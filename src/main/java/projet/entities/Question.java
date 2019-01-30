package projet.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {

	@Id @GeneratedValue
	private Long id_question;
	@Column
	private Date date_creation_question;
	@Column(length=50)
	private String question;
	@Column(length=70)
	private String type;
	@Column
	private Integer points;
	
}
