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

@Entity
@Table(name="scores")
public class Score implements Serializable{
	
	@Id @GeneratedValue
	private Long id_score;
	@Column
	private Integer score;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
	
	@ManyToOne(fetch = FetchType.EAGER) 
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
