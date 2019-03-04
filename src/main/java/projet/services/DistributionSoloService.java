package projet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projet.dao.QuestionRepository;
import projet.dao.QuizzRepository;
import projet.dao.ReponseEleveRepository;
import projet.dao.ReponseRepository;
import projet.dao.UserRepository;
import projet.entities.ReponseEleve;
import projet.entities.User;

@RestController
@CrossOrigin("*")
public class DistributionSoloService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuizzRepository quizzRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ReponseRepository reponseRepository;
	
	@Autowired
	private ReponseEleveRepository repEleRepository;
	
	@RequestMapping(value="/distribtionSolo",method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
	@RequestMapping(value="/distribtionSolo",method=RequestMethod.POST)
    public void passerQuizz( @RequestBody ReponseEleve repEleve ) throws Exception{
		// recupérer l'id de la question
		
		// chercher la Réponse correct de la question
		
		// comparer la réponse de l'eleve avec la réponse correct de la question
		
		//if OK (sauvgarder la reponse eleve en BD)
		
		//if KO renvoyer la meme question
    }
}
