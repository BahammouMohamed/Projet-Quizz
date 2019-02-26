package projet.entities;

import java.io.Serializable;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="scores")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_score")
public class Score implements Serializable{
	
	@Id @GeneratedValue
	private Long id_score;
	@Column
	private Integer score;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_quizz", nullable = false)
	private Quizz quizz;
	
	
	public Score() {
		super();
	}

	
	
	public Score(Integer score, User user, Quizz quizz) {
		super();
		this.score = score;
		this.user = user;
		this.quizz = quizz;
	}



	public Score(Integer score) {
		super();
		this.score = score;
	}


	public Long getId_score() {
		return id_score;
	}


	public Integer getScore() {
		return score;
	}


	public void setId_score(Long id_score) {
		this.id_score = id_score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Quizz getQuizz() {
		return quizz;
	}


	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}
	
	
	
	

}
