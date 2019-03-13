package projet.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projet.dao.QuestionRepository;
import projet.entities.Indice;
import projet.entities.Multimedia;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.Reponse;
import projet.entities.ReponseEleve;
import projet.entities.User;
import projet.exceptions.ResourceNotFoundException;



@RestController
@CrossOrigin("*")
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@RequestMapping(value="/questions",method=RequestMethod.GET)
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
	
	@RequestMapping(value="/questions/{id}",method=RequestMethod.GET)
    public Question getQuestion(@PathVariable Long id) {
        return questionRepository.findById(id).orElse(null);
    }
	
	@RequestMapping(value="/questions",method=RequestMethod.POST)
    public Question createQuestion( @RequestBody Question question) {
        return questionRepository.save(question);
    }

	@RequestMapping(value="/questions/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestions(@PathVariable Long id) {
		//System.err.println("DELETE question 1");
		return questionRepository.findById(id).map(question -> {
			//System.err.println("DELETE question 2");
			questionRepository.delete(question);
			//System.err.println("DELETE question 3");
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new ResourceNotFoundException("QuestionID " + id + " not found"));

    }
	
	@RequestMapping(value="/questions/{id}",method=RequestMethod.PUT)
    public Question updateQuizz(@PathVariable Long id,@RequestBody Question question) {
		return questionRepository.findById(id).map(quest -> {
			quest.setId_question(id);
			quest.setQuestion(question.getQuestion());
			quest.setType(question.getType());
			quest.setPoints(question.getPoints());
			return questionRepository.save(quest);
	    }).orElseThrow(() -> new ResourceNotFoundException("QuestionID " + id + " not found"));
		
	   
    }
	
	@RequestMapping(value="/questions/{id}/quizz",method=RequestMethod.GET)
    public Quizz getQuizz(@PathVariable Long id) {
        return questionRepository.findById(id).get().getQuizz();
    }
	
	@RequestMapping(value="/questions/{id}/quizz/user",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return questionRepository.findById(id).get().getQuizz().getUser();
    }
	
	@RequestMapping(value="/questions/{id}/indices",method=RequestMethod.GET)
    public Set<Indice> getIndices(@PathVariable Long id) {
        return questionRepository.findById(id).get().getIndices();
    }
	
	@RequestMapping(value="/questions/{id}/reponses",method=RequestMethod.GET)
    public Set<Reponse> getReponses(@PathVariable Long id) {
        return questionRepository.findById(id).get().getReponses();
    }
	
	@RequestMapping(value="/questions/{id}/medias",method=RequestMethod.GET)
    public Set<Multimedia> getMedias(@PathVariable Long id) {
        return questionRepository.findById(id).get().getMedias();
    }
	
	@RequestMapping(value="/questions/{id}/reponseseleve",method=RequestMethod.GET)
    public Set<ReponseEleve> getReponsesEleve(@PathVariable Long id) {
        return questionRepository.findById(id).get().getReponsesEleve();
    }
	
	
	public String getCorrectReponse(Long id) {
		Question question = questionRepository.findById(id).get();
		for (Reponse rep : question.getReponses()) {
			if (rep.isCorrect())
				return rep.getReponse();
		}
        return null;
    }
	
}
