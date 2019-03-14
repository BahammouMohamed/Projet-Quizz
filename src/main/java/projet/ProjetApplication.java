package projet;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import projet.dao.IndiceRepository;
import projet.dao.QuestionRepository;
import projet.dao.QuizzRepository;
import projet.dao.ReponseEleveRepository;
import projet.dao.ReponseRepository;
import projet.dao.UserRepository;
import projet.entities.Indice;
import projet.entities.Question;
import projet.entities.Quizz;
import projet.entities.Reponse;
import projet.entities.ReponseEleve;
import projet.entities.Role;
import projet.entities.User;
import projet.services.UserService;


@SpringBootApplication
public class ProjetApplication {
	@Autowired
	private UserRepository userDao ;
	@Autowired
	private QuestionRepository questionDao ;
	@Autowired
	private QuizzRepository quizzDao ;
	@Autowired
	private IndiceRepository indiceDao ;
	@Autowired
	private ReponseEleveRepository repEleveDao ;
	@Autowired
	private ReponseRepository repDao ;
	@Autowired
	private UserService userService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
		System.err.println("SERVER STARTED...");
	}
	

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}


/*
	@Override
	public void run(String... args) throws Exception {

		
		
		
		User user1 = new User("flauzac","olivier","olivier@gmail.com","olivier","olivier","enseignant",false,null);
		User user2 = new User("stefennel","angello","angello@gmail.com","angello","angello","enseignant",false,null);
		User user3 = new User("bernard","thibaud","thibaud@gmail.com","thibaud","thibaud","enseignant",false,null);
		
		User eleve1 = new User("bahammou","mohamed","mohamed@gmail.com","mohamed123","mohamed123","eleve",false,null);
		User eleve2 = new User("alla","reda","reda@gmail.com","reda","reda","eleve",false,null);
		User eleve3 = new User("belghazi","zouhair","zouhair@gmail.com","zouhair","zouhair","eleve",false,null);
		
		
		userService.createUser(user1);
		userService.createUser(user2);
		userService.createUser(user3);
		userService.createUser(eleve1);
		userService.createUser(eleve2);
		userService.createUser(eleve3);
		
		userService.createRole(new Role(null,"ADMIN"));
		userService.createRole(new Role(null,"ELEVE"));
		userService.createRole(new Role(null,"ENSEIGNANT"));
		
		
	userService.addRoleToUser("olivier", "ADMIN");
		userService.addRoleToUser("angello", "ENSEIGNANT");
		userService.addRoleToUser("thibaud", "ENSEIGNANT");
		userService.addRoleToUser("mohamed123", "ELEVE");
		userService.addRoleToUser("reda", "ELEVE");
		userService.addRoleToUser("zouhair", "ELEVE");
		
		
		
		
		
				
		Quizz quizz1 = new Quizz("lycée", "sport", "seance 1",user1);
		Quizz quizz2 = new Quizz("cm1", "physique", "seance 2",user2);
		Quizz quizz3 = new Quizz("cm2", "science", "seance 4",user3);
		Quizz quizz4 = new Quizz("cm2", "science math", "seance 3",user3);
		
		quizzDao.save(quizz1);
		quizzDao.save(quizz2);
		quizzDao.save(quizz3);
		quizzDao.save(quizz4);
		
		Question question1 = new Question("Qui a gagner le Ballon d'OR en 2018 ?", "media", 2,quizz1);
		Question question2 = new Question("Qui a gagner la coupe du monde 2018 ?", "simple", 4,quizz1);
		Question question3 = new Question("Ngolo Kante est de quelle origine ?", "media", 5,quizz1);
		
		Indice indice1_1 = new Indice("Natinnalité : Croate",question1 );
		Indice indice2_1 = new Indice("Il joue actuellement au Real Madrid",question1 );
		Indice indice3_1 = new Indice("Il a 30 ans", question1 );
		
		Indice indice1_2 = new Indice("C'est un pays Europeen", question2 );
		Indice indice2_2 = new Indice("Colé a la Suisse", question2 );
				
		Indice indice1_3 = new Indice("En Afrique", question3  );
		Indice indice2_3 = new Indice("Pas dans le Nord de l'Afrique", question3  );
		
		
		
		Question question4 = new Question("question 4", "media", 2,quizz2);
		Question question5 = new Question( "question 5", "simple", 4,quizz2);
		Question question6 = new Question("question 6", "media", 5,quizz2);
		
		Indice indice1_4 = new Indice("indice 1 question 4", question4 );
		Indice indice1_5 = new Indice("indice 1 question 5", question5 );
		Indice indice1_6 = new Indice("indice 1 question 6", question6);
				
		
		Question question7 = new Question( "question 7", "media", 2,quizz3);
		Question question8 = new Question( "question 8", "simple", 4,quizz3);
		Question question9 = new Question( "question 9", "media", 5,quizz3);
		
		//Indice indice1_7 = new Indice("indice 1 question 7", question7 );
		//Indice indice1_8 = new Indice("indice 1 question 8", question8 );
		Indice indice1_9 = new Indice("indice 1 question 9", question9 );
		
		Question question10 = new Question("question 10", "media10", 5,quizz4);
				
		questionDao.save(question1);
		questionDao.save(question2);
		questionDao.save(question3);
		questionDao.save(question4);
		questionDao.save(question5);
		questionDao.save(question6);
		questionDao.save(question7);
		questionDao.save(question8);
		questionDao.save(question9);
		questionDao.save(question10);
		
		
		indiceDao.save(indice1_1);
		indiceDao.save(indice2_1);
		indiceDao.save(indice3_1);
		indiceDao.save(indice1_2);
		indiceDao.save(indice2_2);
		indiceDao.save(indice1_3);
		indiceDao.save(indice2_3);
		indiceDao.save(indice1_4);
		indiceDao.save(indice1_5);
		indiceDao.save(indice1_6);
		//indiceDao.save(indice1_7);
		//indiceDao.save(indice1_8);
		indiceDao.save(indice1_9);
		
		
		
		Reponse rep1_1 = new Reponse("Lionnel Messi", false, question1);
		Reponse rep2_1 = new Reponse("Cristiano Ronaldo", false, question1);
		Reponse rep3_1 = new Reponse("Luca Modriç", true, question1);
		
		Reponse rep1_2 = new Reponse("France", true, question2);
		Reponse rep2_2 = new Reponse("Espagne", false, question2);
		
		Reponse rep1_3 = new Reponse("Maroc", false, question3);
		Reponse rep2_3 = new Reponse("Mali", true, question3);
		
		Reponse rep1_4 = new Reponse("Reponse 1 question 4", false, question4);
		Reponse rep2_4 = new Reponse("Reponse 2 question 4", true, question4);
		
		Reponse rep1_5 = new Reponse("Reponse 1 question 5", false, question5);
		Reponse rep2_5 = new Reponse("Reponse 2 question 5", true, question5);
		
		Reponse rep1_6 = new Reponse("Reponse 1 question 6", false, question6);
		Reponse rep2_6 = new Reponse("Reponse 2 question 6", true, question6);
		
		Reponse rep1_7 = new Reponse("Reponse 1 question 7", false, question7);
		Reponse rep2_7 = new Reponse("Reponse 2 question 7", true, question7);
		
		Reponse rep1_8 = new Reponse("Reponse 1 question 8", false, question8);
		Reponse rep2_8 = new Reponse("Reponse 2 question 8", true, question8);
		
		Reponse rep1_9 = new Reponse("Reponse 1 question 9", false, question9);
		Reponse rep2_9 = new Reponse("Reponse 2 question 9", true, question9);
		
		Reponse rep1_10 = new Reponse("Reponse 1 question 10", false, question10);
		Reponse rep2_10 = new Reponse("Reponse 2 question 10", true, question10);
		
		repDao.save(rep1_1);repDao.save(rep2_1);repDao.save(rep3_1);
		repDao.save(rep1_2);repDao.save(rep2_2);
		repDao.save(rep1_3);repDao.save(rep2_3);
		repDao.save(rep1_4);repDao.save(rep2_4);
		repDao.save(rep1_5);repDao.save(rep2_5);
		repDao.save(rep1_6);repDao.save(rep2_6);
		repDao.save(rep1_7);repDao.save(rep2_7);
		repDao.save(rep1_8);repDao.save(rep2_8);
		repDao.save(rep1_9);repDao.save(rep2_9);
		repDao.save(rep1_10);repDao.save(rep2_10);
		
		
		
		ReponseEleve repE1_1 = new ReponseEleve("reponse eleve 1 question 1 ", eleve1, question1,true);
		ReponseEleve repE1_2 = new ReponseEleve("reponse eleve 1 question  2", eleve1, question2,false);
		ReponseEleve repE1_3 = new ReponseEleve("reponse eleve 1 question  3", eleve1, question3,true);
		
		ReponseEleve repE2_4 = new ReponseEleve("reponse eleve 2 question 4 ", eleve2, question4,true);
		ReponseEleve repE2_5 = new ReponseEleve("reponse eleve 2 question  5", eleve2, question5,false);
		ReponseEleve repE2_6 = new ReponseEleve("reponse eleve 2 question  6", eleve2, question6,true);
		
		ReponseEleve repE3_7 = new ReponseEleve("reponse eleve 3 question 7 ", eleve3, question7,true);
		ReponseEleve repE3_8 = new ReponseEleve("reponse eleve 3 question 8 ", eleve3, question8,true);
		ReponseEleve repE3_9 = new ReponseEleve("reponse eleve 3 question 9 ", eleve3, question9,false);
		
		repEleveDao.save(repE1_1);
		repEleveDao.save(repE1_2); 
		repEleveDao.save(repE1_3);
		repEleveDao.save(repE2_4);
		repEleveDao.save(repE2_5);
		repEleveDao.save(repE2_6);
		repEleveDao.save(repE3_7);
		repEleveDao.save(repE3_8);
		repEleveDao.save(repE3_9);
		
		
		List<User> users = userDao.findAll();
		users.forEach(u->System.out.println(u.getPseudo()));
		
		User user = userDao.findByPseudo("olivier");
		System.err.println(user.getPseudo());
		System.err.println(user.getEmail());
		Quizz quizz = quizzDao.findById(7L).orElse(null);
        System.err.println(quizz.getMatiere());
        
		System.err.println("SERVER LAUNCHED...");
	}*/
}
