package projet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projet.dao.IndiceRepository;
import projet.dao.ReponseRepository;
import projet.entities.Indice;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.Reponse;
import projet.entities.User;
import projet.exceptions.ResourceNotFoundException;



@RestController
public class ReponseService {
	
	@Autowired
	private ReponseRepository reponseRepository;
	
	@RequestMapping(value="/reponses",method=RequestMethod.GET)
    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }
	
	@RequestMapping(value="/reponses/{id}",method=RequestMethod.GET)
    public Reponse getReponse(@PathVariable Long id) {
        return reponseRepository.findById(id).orElse(null);
    }
	
	@RequestMapping(value="/reponses",method=RequestMethod.POST)
    public Reponse createReponse( @RequestBody Reponse rep) {
        return reponseRepository.save(rep);
    }

	@RequestMapping(value="/reponses/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteReponse(@PathVariable Long id) {
		return reponseRepository.findById(id).map(rep -> {
			reponseRepository.delete(rep);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new ResourceNotFoundException("ReponseID " + id + " not found"));

    }
	
	@RequestMapping(value="/reponses/{id}",method=RequestMethod.PUT)
    public Reponse updateReponse(@PathVariable Long id,@RequestBody Reponse rep) {
		return reponseRepository.findById(id).map(temp -> {
			temp.setCorrect(rep.isCorrect());
			temp.setReponse(rep.getReponse());
	        return reponseRepository.save(temp);
	    }).orElseThrow(() -> new ResourceNotFoundException("ReponseID " + id + " not found"));
    }
	
	@RequestMapping(value="/reponses/{id}/question",method=RequestMethod.GET)
    public Question getQuestion(@PathVariable Long id) {
        return reponseRepository.findById(id).get().getQuestion();
    }
	
	@RequestMapping(value="/reponses/{id}/question/quizz",method=RequestMethod.GET)
    public Quizz getQuizz(@PathVariable Long id) {
        return reponseRepository.findById(id).get().getQuestion().getQuizz();
    }
	
	@RequestMapping(value="/reponses/{id}/question/quizz/user",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return reponseRepository.findById(id).get().getQuestion().getQuizz().getUser();
    }
	
	
	
	
	
}
