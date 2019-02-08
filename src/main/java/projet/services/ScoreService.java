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
import projet.dao.ScoreRepository;
import projet.entities.Indice;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.Score;
import projet.entities.User;
import projet.exceptions.ResourceNotFoundException;



@RestController
public class ScoreService {
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@RequestMapping(value="/scores",method=RequestMethod.GET)
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }
	
	@RequestMapping(value="/scores/{id}",method=RequestMethod.GET)
    public Score getScore(@PathVariable Long id) {
        return scoreRepository.findById(id).orElse(null);
    }
	
	@RequestMapping(value="/scores",method=RequestMethod.POST)
    public Score createScore( @RequestBody Score score) {
        return scoreRepository.save(score);
    }

	@RequestMapping(value="/scores/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteScore(@PathVariable Long id) {
		return scoreRepository.findById(id).map(score -> {
			scoreRepository.delete(score);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new ResourceNotFoundException("IndiceID " + id + " not found"));

    }
	
	@RequestMapping(value="/scores/{id}",method=RequestMethod.PUT)
    public Score updateScore(@PathVariable Long id,@RequestBody Score score) {
		return scoreRepository.findById(id).map(sco -> {
			sco.setScore(score.getScore());
	        return scoreRepository.save(sco);
	    }).orElseThrow(() -> new ResourceNotFoundException("IndiceID " + id + " not found"));
    }
	
	@RequestMapping(value="/scores/{id}/user",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return scoreRepository.findById(id).get().getUser();
    }
	
	@RequestMapping(value="/scores/{id}/quizz",method=RequestMethod.GET)
    public Quizz getQuizz(@PathVariable Long id) {
        return scoreRepository.findById(id).get().getQuizz();
    }
	
	
	
	
	
	
	
}
