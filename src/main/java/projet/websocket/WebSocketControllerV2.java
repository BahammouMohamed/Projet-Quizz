package projet.websocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import projet.dao.QuizzRepository;
import projet.dao.ReponseEleveRepository;
import projet.dao.UserRepository;
import projet.entities.Indice;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.Reponse;
import projet.entities.ReponseEleve;
import projet.entities.User;
import projet.services.QuestionService;

@CrossOrigin("*")
@Controller
public class WebSocketControllerV2 {

    private final SimpMessagingTemplate template;
    @Autowired
    private QuizzRepository quizzRepository; 
    
    @Autowired
    private UserRepository userRepository; 
    
    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private ReponseEleveRepository repEleveRepository;
    
    private Map<Long, LinkedList<Question>> mapQuizzs = new HashMap<>();
        
    @Autowired
    WebSocketControllerV2(SimpMessagingTemplate template){
        this.template = template;
    }
    
    
    @MessageMapping("/V2/new/quizz/{idQuizz}/{idUser}")
    public void onNouveauQuizz(String message , @DestinationVariable String idQuizz, @DestinationVariable String idUser){
    	
    	Random rand = new Random();
    	int num = rand.nextInt(900000) + 100000;
    	
    	while (mapQuizzs.get(Long.parseLong(""+num)) != null) {
    		rand = new Random();
        	num = rand.nextInt(90000) + 10000;
    	}
    	System.out.println("onNouveauQuizz num = " + num);
    	Quizz qz = new Quizz();
    	
		qz =  quizzRepository.findById(Long.parseLong(idQuizz)).orElse(null);
		if (qz != null) {
			Set<Question> tmp = qz.getQuestions();
			LinkedList<Question> llQuestions = new LinkedList<>();
			for (Question question : tmp)
				llQuestions.add(question);
			
			mapQuizzs.put(Long.parseLong(""+num), llQuestions);
			
        	this.template.convertAndSend("/competition/V2/"+idQuizz+"/"+idUser,  num);
		}
    	
    }
    
    @MessageMapping("/V2/load/quizz/{idPartie}")
    public void onLoadQuizz(String message , @DestinationVariable String idPartie){
    	
    	System.err.println("J'AI RECU UNE DEMANDE DE LOAD DU QUIZZ "+message+" : JE VAIS LOADER LE QUIZZ ICI");
    	if (mapQuizzs.get(Long.parseLong(idPartie)) != null)
    	{
			Question questCourrante = mapQuizzs.get(Long.parseLong(idPartie)).peek();

			String qst = questCourrante.getQuestion();
			System.err.println("onLoadQuizz ?????? Current Question = " + qst);
			JsonArray indArray = new JsonArray();
			JsonArray repArray = new JsonArray();

			for (Indice ind : questCourrante.getIndices())
				indArray.add(ind.getIndice());
			for (Reponse rep : questCourrante.getReponses())
				repArray.add(rep.getReponse());

			JSONObject objet = new JSONObject();
			objet.put("id_question", questCourrante.getId_question());
			objet.put("question", qst);
			objet.put("indices", indArray);
			objet.put("reponses", repArray);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(objet);

			this.template.convertAndSend("/competition/V2/partie/" + idPartie, "load-disable");
			this.template.convertAndSend("/competition/V2/partie/" + idPartie, jsonString);
    		
    	}
    }
    
    @MessageMapping("/V2/{idPartie}")
    public void onReceivedMesage(String message, @DestinationVariable String idPartie){
    	Question questCourrante = mapQuizzs.get(Long.parseLong(idPartie)).peek();
    	String repCorrect = questionService.getCorrectReponse(questCourrante.getId_question());
    	System.out.println("MESSAGE RECU : " + message+" REPONSE CORRECT = "+repCorrect);
    	
    	JSONObject jsonObj = new JSONObject(message);
    	String repEleve = (String) jsonObj.get("reponse_eleve");
    	Long idUser = Long.parseLong(jsonObj.get("user").toString());
    	
    	if(repCorrect != null) {
    		if (repEleve.equals(repCorrect)) {
    			   	    	
    	    	User userCourant = userRepository.findById(idUser).get();
    	    	ReponseEleve repEle = new ReponseEleve(repEleve, userCourant, questCourrante,true);
    	    	repEleveRepository.save(repEle);
				System.out.println("Current = " + questCourrante.getQuestion());
				for(Iterator<Question> it=mapQuizzs.get(Long.parseLong(idPartie)).iterator(); it.hasNext(); ) {
				    if(it.next().getId_question()==questCourrante.getId_question()) { 
				        it.remove(); 
				        break;
				        }
				    }
				if (mapQuizzs.get(Long.parseLong(idPartie)).size()>0) {
					questCourrante = mapQuizzs.get(Long.parseLong(idPartie)).peek();
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
                	
    	        	this.template.convertAndSend("/competition/V2/partie/"+idPartie,  jsonString);
				} else {
		    		System.err.println("**********************************");
		    		System.err.println("************FIN DU QUIZZ**********");
		    		System.err.println("**********************************");
		    		this.template.convertAndSend("/competition/V2/partie/"+idPartie,  "fin-quizz");
		    	}
    				
			} else {
    			this.template.convertAndSend("/competition/V2/partie/"+idPartie,  "mauvaise-reponse");
    		}
    	} 
	}	
}

