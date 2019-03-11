package projet.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import projet.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import projet.dao.UserRepository;
import projet.entities.Quizz;
import projet.entities.ReponseEleve;
import projet.entities.Score;
import projet.entities.User;



@RestController
@CrossOrigin("*")
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
		User user = new User();
		user = userRepository.findById(id).orElse(null);
		user.setPassword("");
        return user;
    }
	
	@RequestMapping(value="/users",method=RequestMethod.POST)
    public User createUser( @RequestBody User user) throws Exception{
		User usertmp = userRepository.findByPseudo(user.getPseudo());
		if(usertmp!=null)
			throw new Exception("Pseudo existe déja");
		usertmp = userRepository.findByEmail(user.getEmail());
		if(usertmp != null)
			throw new Exception("Email existe déja");
		return userRepository.save(user);
    }

	@RequestMapping(value="/users/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		return userRepository.findById(id).map(user -> {
			userRepository.delete(user);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new ResourceNotFoundException("UserID " + id + " not found"));

    }
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.PUT)
    public User updateUser(@PathVariable Long id,@RequestBody User userRequest) {
		return userRepository.findById(id).map(user -> {
			user.setNom(userRequest.getNom());
			user.setPrenom(userRequest.getPrenom());
			user.setPseudo(userRequest.getPseudo());
			user.setPassword(userRequest.getPassword());
			user.setEmail(userRequest.getEmail());
			user.setStatus(userRequest.getStatus());
			user.setValidated(false);
	        return userRepository.save(user);
	    }).orElseThrow(() -> new ResourceNotFoundException("UserID " + id + " not found"));

    }
	
	@RequestMapping(value="/users/{id}/quizzs",method=RequestMethod.GET)
    public Set<Quizz> getQuizzs(@PathVariable Long id) {
        return userRepository.findById(id).get().getQuizzs();
    }
	
	@RequestMapping(value="/users/{id}/reponseseleve",method=RequestMethod.GET)
    public Set<ReponseEleve> getReponsesEleve(@PathVariable Long id) {
        return userRepository.findById(id).get().getReponses();
    }
	
	@RequestMapping(value="/users/{id}/scores",method=RequestMethod.GET)
    public Set<Score> getScores(@PathVariable Long id) {
        return userRepository.findById(id).get().getScores();
    }
	
}
