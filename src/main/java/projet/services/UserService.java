package projet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import projet.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import projet.dao.UserRepository;
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
	        return userRepository.save(user);
	    }).orElseThrow(() -> new ResourceNotFoundException("UserID " + id + " not found"));

    }
	
}
