package projet.websocket;


import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import projet.dao.QuizzRepository;
import projet.entities.Indice;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.Reponse;
import projet.services.QuestionService;

@Controller
public class WebSocketControllerBackUp {
/*
    private final SimpMessagingTemplate template;
    @Autowired
    private QuizzRepository quizzRepository; 
    
    @Autowired
    private QuestionService questionService;
    
    private volatile Quizz quizz;
    private volatile Set<Question> lstQuestion;
    private volatile Iterator<Question> itQuestions;
    private volatile Question currentQuestion = new Question();
    
    @Autowired
    WebSocketControllerBackUp(SimpMessagingTemplate template){
        this.template = template;
    }
    
    @MessageMapping("/load/quizz/{idQuizz}")
    public void onLoadQuizz(String message , @DestinationVariable String idQuizz){
        // ici récupérer le quizz 
    	System.err.println("J'AI RECU UNE DEMANDE DE LOAD DU QUIZZ "+message+" : JE VAIS LOADER LE QUIZZ ICI");
    	quizz =  quizzRepository.findById(Long.parseLong(idQuizz)).orElse(null);
    	lstQuestion =  quizz.getQuestions();
    	itQuestions = lstQuestion.iterator();
    	currentQuestion = itQuestions.next();
    	String qst = currentQuestion.getQuestion();
    	System.err.println("onLoadQuizz ?????? Current Question = " + qst);
    	JsonArray indArray = new JsonArray();
    	JsonArray repArray = new JsonArray();
    	        	
    	for (Indice ind : currentQuestion.getIndices()) 
    		indArray.add(ind.getIndice());
    	for (Reponse rep : currentQuestion.getReponses())
    		repArray.add(rep.getReponse());
    	
    	JSONObject objet = new JSONObject();
    	objet.put("id_question", currentQuestion.getId_question() );
    	objet.put("question", qst);
    	objet.put("indices", indArray);
    	objet.put("reponses", repArray);
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	String jsonString = gson.toJson(objet);
    	
    	this.template.convertAndSend("/competition/"+idQuizz,  "load-disable");
    	this.template.convertAndSend("/competition/"+idQuizz,  jsonString);
    }
    
    @MessageMapping("/{idQuizz}")
    public void onReceivedMesage(String message, @DestinationVariable String idQuizz){
    	String repCorrect = questionService.getCorrectReponse(currentQuestion.getId_question());
    	System.out.println("MESSAGE RECU : " + message);
    	if(repCorrect != null) {
    		if (message.equals(repCorrect)) {
    			if (itQuestions.hasNext()) {
    	    		currentQuestion = itQuestions.next();
    	    		String qst = currentQuestion.getQuestion();
    	        	System.err.println("onReceivedMesage ?????? Current Question = " + qst);
    	    		JsonArray indArray = new JsonArray();
    	        	JsonArray repArray = new JsonArray();
    	        	        	
    	        	for (Indice ind : currentQuestion.getIndices()) 
    	        		indArray.add(ind.getIndice());
    	        	for (Reponse rep : currentQuestion.getReponses())
    	        		repArray.add(rep.getReponse());
    	        	JSONObject objet = new JSONObject();
    	        	objet.put("id_question", currentQuestion.getId_question() );
    	        	objet.put("question", qst);
    	        	objet.put("indices", indArray);
    	        	objet.put("reponses", repArray);
    	        	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	        	String jsonString = gson.toJson(objet);
    	        	
    	        	this.template.convertAndSend("/competition/"+idQuizz,  jsonString);
    	    	} else {
    	    		System.err.println("**********************************");
    	    		System.err.println("************FIN DU QUIZZ**********");
    	    		System.err.println("**********************************");
    	    		this.template.convertAndSend("/competition/"+idQuizz,  "fin-quizz");
    	    	}
    		} else {
    			this.template.convertAndSend("/competition/"+idQuizz,  "mauvaise-reponse");
    		}
    	}
    	// TODO Algorithme du mode competition
    	
    	
    	// TODO ici construire l'objet JSON : la question + ses reponses + ses indices
    	
    	
    	
    }
*/
}
