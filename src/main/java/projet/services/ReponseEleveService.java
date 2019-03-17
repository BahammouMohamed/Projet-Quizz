package projet.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projet.dao.ReponseEleveRepository;
import projet.entities.Question;
import projet.entities.ReponseEleve;
import projet.entities.User;
import projet.exceptions.ResourceNotFoundException;

@RestController
@CrossOrigin("*")
public class ReponseEleveService {
	@Autowired
	private ReponseEleveRepository repEleRepository ;
	
	
	@RequestMapping(value="/reponseseleve",method=RequestMethod.GET)
    public List<ReponseEleve> getAllReponsesEleve() {
        return repEleRepository.findAll();
    }
	
	@RequestMapping(value="/reponseseleve/{id}",method=RequestMethod.GET)
    public ReponseEleve getReponseEleve(@PathVariable Long id) {
        return repEleRepository.findById(id).orElse(null);
    }
	
	@RequestMapping(value="/reponseseleve",method=RequestMethod.POST)
    public ReponseEleve createReponseEleve( @RequestBody ReponseEleve repEle) {
        return repEleRepository.save(repEle);
    }

	@RequestMapping(value="/reponseseleve/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteReponseEleve(@PathVariable Long id) {
		return repEleRepository.findById(id).map(repEle -> {
			repEleRepository.delete(repEle);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new ResourceNotFoundException("ReponseEleveID " + id + " not found"));

    }
	
	@RequestMapping(value="/reponseseleve/{id}",method=RequestMethod.PUT)
    public ReponseEleve updateReponseEleve(@PathVariable Long id,@RequestBody ReponseEleve rep) {
		return repEleRepository.findById(id).map(repEle -> {
			repEle.setReponse_eleve(rep.getReponse_eleve());
	        return repEleRepository.save(repEle);
	    }).orElseThrow(() -> new ResourceNotFoundException("ReponseEleveID " + id + " not found"));
    }
	
	@RequestMapping(value="/reponseseleve/{id}/question",method=RequestMethod.GET)
    public Question getQuestion(@PathVariable Long id) {
        return repEleRepository.findById(id).get().getQuestion();
    }
	
	@RequestMapping(value="/reponseseleve/{id}/user",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return repEleRepository.findById(id).get().getUser();
    }
	
	
	
	
}
