package projet.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projet.dao.QuizzRepository;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.exceptions.ResourceNotFoundException;



@RestController
public class QuizzService {
	
	@Autowired
	private QuizzRepository quizzRepository;
	
	@RequestMapping(value="/quizzs",method=RequestMethod.GET)
    public List<Quizz> getAllQuizzs() {
        return quizzRepository.findAll();
    }
	
	@RequestMapping(value="/quizzs/{id}",method=RequestMethod.GET)
    public Quizz getQuizz(@PathVariable Long id) {
        return quizzRepository.findById(id).orElse(null);
    }
	
	@RequestMapping(value="/quizzs",method=RequestMethod.POST)
    public Quizz createQuizz( @RequestBody Quizz quizz) {
        return quizzRepository.save(quizz);
    }

	@RequestMapping(value="/quizzs/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuizz(@PathVariable Long id) {
		return quizzRepository.findById(id).map(quizz -> {
			quizzRepository.delete(quizz);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new ResourceNotFoundException("QuizzID " + id + " not found"));

    }
	
	@RequestMapping(value="/quizzs/{id}",method=RequestMethod.PUT)
    public Quizz updateQuizz(@PathVariable Long id,@RequestBody Quizz quizz) {
		return quizzRepository.findById(id).map(qui -> {
			qui.setId_quizz(id);
			qui.setDate_creation_quizz(qui.getDate_creation_quizz());
			qui.setMatiere(quizz.getMatiere());
			qui.setNiveau(quizz.getNiveau());
			qui.setPeriode(quizz.getPeriode());
	        return quizzRepository.save(qui);
	    }).orElseThrow(() -> new ResourceNotFoundException("QuizzID " + id + " not found"));
		
	   
    }
	
	@RequestMapping(value="/quizzs/{id}/questions",method=RequestMethod.GET)
    public Set<Question> getQuestions(@PathVariable Long id) {
        return quizzRepository.findById(id).get().getQuestions();
    }
	
}
