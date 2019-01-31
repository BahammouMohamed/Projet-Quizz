package projet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="scores")
public class Score {
	
	@Id @GeneratedValue
	private Long id_score;
	@Column
	private Integer score;
	
	
	/**
	 * Ici mapper les clés etranger id_user et id_quizz
	 */
	public Score() {
		super();
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
	
	
	
	

}
