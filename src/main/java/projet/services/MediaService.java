package projet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import projet.dao.MultimediaRepository;
import projet.entities.Multimedia;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.User;
import projet.exceptions.ResourceNotFoundException;



@RestController
public class MediaService {
	
	@Autowired
	private MultimediaRepository mediaRepository;
	
	@RequestMapping(value="/medias",method=RequestMethod.GET)
    public List<Multimedia> getAllIndices() {
        return mediaRepository.findAll();
    }
	
	@RequestMapping(value="/medias/{id}",method=RequestMethod.GET)
    public Multimedia getIndice(@PathVariable Long id) {
        return mediaRepository.findById(id).orElse(null);
    }
	
	@RequestMapping(value="/medias",method=RequestMethod.POST)
    public Multimedia createIndice( @RequestBody Multimedia media) {
        return mediaRepository.save(media);
    }

	@RequestMapping(value="/medias/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuizz(@PathVariable Long id) {
		return mediaRepository.findById(id).map(media -> {
			mediaRepository.delete(media);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new ResourceNotFoundException("MultimediaID " + id + " not found"));

    }
	
	@RequestMapping(value="/medias/{id}",method=RequestMethod.PUT)
    public Multimedia updateQuizz(@PathVariable Long id,@RequestBody Multimedia media) {
		return mediaRepository.findById(id).map(med -> {
			med.setPath_media(media.getPath_media());
			med.setType_media(media.getType_media());
	        return mediaRepository.save(med);
	    }).orElseThrow(() -> new ResourceNotFoundException("MultimediaID " + id + " not found"));
    }
	
	@RequestMapping(value="/medias/{id}/question",method=RequestMethod.GET)
    public Question getQuestion(@PathVariable Long id) {
        return mediaRepository.findById(id).get().getQuestion();
    }
	
	@RequestMapping(value="/medias/{id}/question/quizz",method=RequestMethod.GET)
    public Quizz getQuizz(@PathVariable Long id) {
        return mediaRepository.findById(id).get().getQuestion().getQuizz();
    }
	
	@RequestMapping(value="/medias/{id}/question/quizz/user",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return mediaRepository.findById(id).get().getQuestion().getQuizz().getUser();
    }
	
	
	
	
	
}
