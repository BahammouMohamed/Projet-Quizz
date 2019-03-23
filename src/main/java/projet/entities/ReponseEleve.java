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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reponses_eleves")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReponseEleve implements Serializable{
	
	@Id @GeneratedValue
	private Long id_reponse_eleve;
	@Column
	private String reponse_eleve;
	@Column
	private boolean isCorrect;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
	private Question question;
	
	
	public ReponseEleve(String reponse_eleve) {
		super();
		this.reponse_eleve = reponse_eleve;
	}
	
	public ReponseEleve(String reponse_eleve, User user, Question question, boolean correct) {
		super();
		this.reponse_eleve = reponse_eleve;
		this.user = user;
		this.question = question;
		this.isCorrect = correct;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
