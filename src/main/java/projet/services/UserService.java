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
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }
	
	@RequestMapping(value="/users",method=RequestMethod.POST)
    public User createUser( @RequestBody User user) {
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
			user.setNom_user(userRequest.getNom_user());
			user.setPrenom_user(userRequest.getPrenom_user());
			user.setPseudo_user(userRequest.getPseudo_user());
			user.setPassword_user(userRequest.getPassword_user());
			user.setEmail_user(userRequest.getEmail_user());
			user.setStatus(userRequest.getStatus());
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
