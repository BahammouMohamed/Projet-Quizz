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
import projet.entities.Indice;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.User;
import projet.exceptions.ResourceNotFoundException;



@RestController
public class IndiceService {
	
	@Autowired
	private IndiceRepository indiceRepository;
	
	@RequestMapping(value="/indices",method=RequestMethod.GET)
    public List<Indice> getAllIndices() {
        return indiceRepository.findAll();
    }
	
	@RequestMapping(value="/indices/{id}",method=RequestMethod.GET)
    public Indice getIndice(@PathVariable Long id) {
        return indiceRepository.findById(id).orElse(null);
    }
	
	@RequestMapping(value="/indices",method=RequestMethod.POST)
    public Indice createIndice( @RequestBody Indice indice) {
        return indiceRepository.save(indice);
    }

	@RequestMapping(value="/indices/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuizz(@PathVariable Long id) {
		return indiceRepository.findById(id).map(indice -> {
			indiceRepository.delete(indice);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new ResourceNotFoundException("IndiceID " + id + " not found"));

    }
	
	@RequestMapping(value="/indices/{id}",method=RequestMethod.PUT)
    public Indice updateQuizz(@PathVariable Long id,@RequestBody Indice indice) {
		return indiceRepository.findById(id).map(ind -> {
			ind.setIndice(indice.getIndice());
	        return indiceRepository.save(ind);
	    }).orElseThrow(() -> new ResourceNotFoundException("IndiceID " + id + " not found"));
    }
	
	@RequestMapping(value="/indices/{id}/question",method=RequestMethod.GET)
    public Question getQuestion(@PathVariable Long id) {
        return indiceRepository.findById(id).get().getQuestion();
    }
	
	@RequestMapping(value="/indices/{id}/question/quizz",method=RequestMethod.GET)
    public Quizz getQuizz(@PathVariable Long id) {
        return indiceRepository.findById(id).get().getQuestion().getQuizz();
    }
	
	@RequestMapping(value="/indices/{id}/question/quizz/user",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return indiceRepository.findById(id).get().getQuestion().getQuizz().getUser();
    }
	
	
	
	
	
}
