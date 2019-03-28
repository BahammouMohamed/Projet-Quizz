package projet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import projet.dao.MultimediaRepository;
import projet.dao.QuestionRepository;
import projet.entities.Multimedia;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.User;
import projet.exceptions.ResourceNotFoundException;

import org.springframework.ui.Model;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;





@RestController
@CrossOrigin("*")
public class MediaService {
	
	@Autowired
	private MultimediaRepository mediaRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	/*@RequestMapping(value="/medias",method=RequestMethod.GET)
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
    
    */
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	StorageService storageService = new StorageService();
	 
	 List<String> files = new ArrayList<String>();
	 
	 @PostMapping("/medias")
	 public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("question") String idquestion) {
	 String message = "";
	 	 
	 
	 try {
	 storageService.store(file);
	 files.add(file.getOriginalFilename());
	 
	 message = "Fichier ajouté avec succès ! ";
	 Question quest = questionRepository.findById(Long.parseLong(idquestion)).get();
	 Multimedia multi = new Multimedia(file.getOriginalFilename(), "img", quest);
	 mediaRepository.save(multi);
	 return ResponseEntity.status(HttpStatus.OK).body(message);
	 } catch (Exception e) {
	 message = "FAIL to upload " + file.getOriginalFilename() + "!";
	 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	 }
	 }
	 
	 @GetMapping("/getallfiles")
	 public ResponseEntity<List<String>> getListFiles(Model model) {
	 List<String> fileNames = files
	 .stream().map(fileName -> MvcUriComponentsBuilder
	 .fromMethodName(Multimedia.class, "getFile", fileName).build().toString())
	 .collect(Collectors.toList());
	 
	 return ResponseEntity.ok().body(fileNames);
	 }
	 
	 @GetMapping("/files/{filename:.+}")
	 @ResponseBody
	 public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	 Resource file = storageService.loadFile(filename);
	 return ResponseEntity.ok()
	 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
	 .body(file);
	 }
	 
	 
}
