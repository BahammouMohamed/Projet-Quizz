package projet.websocket;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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
public class WebSocketController {

    private final SimpMessagingTemplate template;
    @Autowired
    private QuizzRepository quizzRepository; 
    
    @Autowired
    private QuestionService questionService;
    
    private Map<Long, LinkedList<Question>> mapQuizzs = new HashMap<>();
        
    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }
    
    @MessageMapping("/load/quizz/{idQuizz}")
    public void onLoadQuizz(String message , @DestinationVariable String idQuizz){
        // ici récupérer le quizz 
    	System.err.println("J'AI RECU UNE DEMANDE DE LOAD DU QUIZZ "+message+" : JE VAIS LOADER LE QUIZZ ICI");
    	Quizz qz = new Quizz();
    	if (mapQuizzs.get(Long.parseLong(idQuizz)) == null)
    	{
    		qz =  quizzRepository.findById(Long.parseLong(idQuizz)).orElse(null);
    		if (qz != null) {
    			Set<Question> tmp = qz.getQuestions();
    			LinkedList<Question> llQuestions = new LinkedList<>();
    			for (Question question : tmp)
    				llQuestions.add(question);
    			
    			mapQuizzs.put(Long.parseLong(idQuizz), llQuestions);
    			
    			Question questCourrante = mapQuizzs.get(Long.parseLong(idQuizz)).peek();
            	
            	String qst = questCourrante.getQuestion();
            	System.err.println("onLoadQuizz ?????? Current Question = " + qst);
            	JsonArray indArray = new JsonArray();
            	JsonArray repArray = new JsonArray();
            	        	
            	for (Indice ind : questCourrante.getIndices()) 
            		indArray.add(ind.getIndice());
            	for (Reponse rep : questCourrante.getReponses())
            		repArray.add(rep.getReponse());
            	
            	JSONObject objet = new JSONObject();
            	objet.put("id_question", questCourrante.getId_question() );
            	objet.put("question", qst);
            	objet.put("indices", indArray);
            	objet.put("reponses", repArray);
            	Gson gson = new GsonBuilder().setPrettyPrinting().create();
            	String jsonString = gson.toJson(objet);
            	
            	// mapQuizzs.get(Long.parseLong(idQuizz)).remove();
            	
            	this.template.convertAndSend("/competition/"+idQuizz,  "load-disable");
            	this.template.convertAndSend("/competition/"+idQuizz,  jsonString);
    		}
    	}
    }
    
    @MessageMapping("/{idQuizz}")
    public void onReceivedMesage(String message, @DestinationVariable String idQuizz){
    	Question questCourrante = mapQuizzs.get(Long.parseLong(idQuizz)).peek();
    	String repCorrect = questionService.getCorrectReponse(questCourrante.getId_question());
    	System.out.println("MESSAGE RECU : " + message+" REPONSE CORRECT = "+repCorrect);
    	
    	System.out.println("***********BEFORE REMOVE*************");
    	for (Question q :  mapQuizzs.get(Long.parseLong(idQuizz))) {
    		System.out.println("QUESTION : "+q.getQuestion() );
		}
    	if(repCorrect != null) {
    		//System.out.println("TEST = " + questCourrante.getQuestion());
    		if (message.equals(repCorrect)) {
    				System.out.println("Current = " + questCourrante.getQuestion());
    				for(Iterator<Question> it=mapQuizzs.get(Long.parseLong(idQuizz)).iterator(); it.hasNext(); ) {
    				    if(it.next().getId_question()==questCourrante.getId_question()) { 
    				        it.remove(); 
    				        break;
    				        }
    				    }
    				if (mapQuizzs.get(Long.parseLong(idQuizz)).size()>0) {
    					questCourrante = mapQuizzs.get(Long.parseLong(idQuizz)).peek();
        	    		String qst = questCourrante.getQuestion();
        	    		System.out.println("Next = " + qst);
                    	JsonArray indArray = new JsonArray();
                    	JsonArray repArray = new JsonArray();
                    	        	
                    	for (Indice ind : questCourrante.getIndices()) 
                    		indArray.add(ind.getIndice());
                    	for (Reponse rep : questCourrante.getReponses())
                    		repArray.add(rep.getReponse());
                    	
                    	JSONObject objet = new JSONObject();
                    	objet.put("id_question", questCourrante.getId_question() );
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
	}	
}

