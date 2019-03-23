package projet.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projet.dao.RoleRepository;
import projet.dao.UserRepository;
import projet.entities.Quizz;
import projet.entities.ReponseEleve;
import projet.entities.Role;
import projet.entities.Score;
import projet.entities.User;
import projet.exceptions.ResourceNotFoundException;



@RestController
@CrossOrigin("*")
@Service
@Transactional
public class UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
		User user = new User();
		user = userRepository.findById(id).orElse(null);
        return user;
    }
	
	@RequestMapping(value="/users/username/{pseudo}",method=RequestMethod.GET)
    public User getUserByPseudo(@PathVariable String pseudo) {
		User user = new User();
		user = userRepository.findByPseudo(pseudo);
        return user;
    }
	@RequestMapping(value="/inscription",method=RequestMethod.POST)
	public User inscription( @RequestBody User user) throws Exception{
		User usertmp = userRepository.findByPseudo(user.getPseudo());
		if(usertmp!=null)
			throw new Exception("Pseudo existe déja");
		usertmp = userRepository.findByEmail(user.getEmail());
		if(usertmp != null)
			throw new Exception("Email existe déja");
		try {
		String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		}catch (Exception e) {
			System.err.println("************************"+e+"****************************");
		}
		usertmp = userRepository.save(user);
		
		this.addRoleToUser(usertmp.getPseudo(), usertmp.getStatus().toUpperCase());
		
		return usertmp;
    }
    
	
	
	public User createUser( User user) throws Exception{
		User usertmp = userRepository.findByPseudo(user.getPseudo());
		if(usertmp!=null)
			throw new Exception("Pseudo existe déja");
		usertmp = userRepository.findByEmail(user.getEmail());
		if(usertmp != null)
			throw new Exception("Email existe déja");
		try {
		String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		}catch (Exception e) {
			System.err.println("************************"+e+"****************************");
		}
		
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
			String hashPW=bCryptPasswordEncoder.encode(userRequest.getPassword());
			user.setPassword(hashPW);
			user.setEmail(userRequest.getEmail());
			user.setStatus(userRequest.getStatus());
			user.setValidated(false);
	        return userRepository.save(user);
	    }).orElseThrow(() -> new ResourceNotFoundException("UserID " + id + " not found"));

    }
	
	@RequestMapping(value="/users/{id}/validate",method=RequestMethod.GET)
    public User validateUser(@PathVariable Long id) {
		return userRepository.findById(id).map(user -> {
			user.setValidated(true);
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
	
	@RequestMapping(value="/roles",method=RequestMethod.POST)
	public Role createRole(Role role) {
		
		return roleRepository.save(role);
	}
	
	
	@RequestMapping(value="/users-roles",method=RequestMethod.POST)
	public void addRoleToUser(String pseudo, String roleName) {
	
		Role role = roleRepository.findByRoleName(roleName);
		User user = userRepository.findByPseudo(pseudo);
		user.getRoles().add(role);
		
	}
	
	
	public User findUserByPseudo(String pseudo) {
		
		return userRepository.findByPseudo(pseudo);
	}
}
