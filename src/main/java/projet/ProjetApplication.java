package projet;


import projet.dao.IndiceRepository;
import projet.dao.QuestionRepository;
import projet.dao.QuizzRepository;
import projet.dao.ReponseEleveRepository;
import projet.dao.ReponseRepository;
import projet.dao.UserRepository;
import projet.entities.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class ProjetApplication implements CommandLineRunner{
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
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
			
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("bahammou","mohamed","mohamed@gmail.com","mohamed123","mohamed123");
		User user2 = new User("alla","reda","reda@gmail.com","reda123","reda123");
		User user3 = new User("belghazi","zouhair","zouhair@gmail.com","zouhair123","zouhair123");
		
		
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);
		user2.setNom_user("alla2");
		user2.setPrenom_user("reda2");
		userDao.save(user2);
		
				
		Quizz quizz1 = new Quizz("bac", "math", "seance 1", new Date(),user1);
		Quizz quizz2 = new Quizz("cm1", "physique", "seance 2", new Date(),user2);
		Quizz quizz3 = new Quizz("cm2", "science", "seance 4", new Date(),user3);
		Quizz quizz4 = new Quizz("cm2", "science math", "seance 3", new Date(),user3);
		
		quizzDao.save(quizz1);
		quizzDao.save(quizz2);
		quizzDao.save(quizz3);
		quizzDao.save(quizz4);
		
		Question question1 = new Question(new Date(), "question 1", "media", 2,quizz1);
		Question question2 = new Question(new Date(), "question 2", "simple", 4,quizz1);
		Question question3 = new Question(new Date(), "question 3", "media", 5,quizz1);
		
		Indice indice1_1 = new Indice("indice 1 question 1", new Date(),question1 );
		Indice indice2_1 = new Indice("indice 2 question 1", new Date(),question1 );
		Indice indice3_1 = new Indice("indice 3 question 1", new Date(),question1 );
		
		Indice indice1_2 = new Indice("indice 1 question 2", new Date(),question2 );
		Indice indice2_2 = new Indice("indice 2 question 2", new Date(),question2 );
				
		Indice indice1_3 = new Indice("indice 1 question 3", new Date(),question3  );
		Indice indice2_3 = new Indice("indice 2 question 3", new Date(),question3  );
		
		
		
		Question question4 = new Question(new Date(), "question 4", "media", 2,quizz2);
		Question question5 = new Question(new Date(), "question 5", "simple", 4,quizz2);
		Question question6 = new Question(new Date(), "question 6", "media", 5,quizz2);
		
		Indice indice1_4 = new Indice("indice 1 question 4", new Date(),question4 );
		Indice indice1_5 = new Indice("indice 1 question 5", new Date(),question5 );
		Indice indice1_6 = new Indice("indice 1 question 6", new Date() ,question6);
				
		
		Question question7 = new Question(new Date(), "question 7", "media", 2,quizz3);
		Question question8 = new Question(new Date(), "question 8", "simple", 4,quizz3);
		Question question9 = new Question(new Date(), "question 9", "media", 5,quizz3);
		
		Indice indice1_7 = new Indice("indice 1 question 7", new Date(),question7 );
		Indice indice1_8 = new Indice("indice 1 question 8", new Date(),question8 );
		Indice indice1_9 = new Indice("indice 1 question 9", new Date(),question9 );
		
		
				
		questionDao.save(question1);
		questionDao.save(question2);
		questionDao.save(question3);
		questionDao.save(question4);
		questionDao.save(question5);
		questionDao.save(question6);
		questionDao.save(question7);
		questionDao.save(question8);
		questionDao.save(question9);
		
		
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
		indiceDao.save(indice1_7);
		indiceDao.save(indice1_8);
		indiceDao.save(indice1_9);
		
		
		
		Reponse rep1_1 = new Reponse("Reponse 1 question 1", false, new Date(), question1);
		Reponse rep2_1 = new Reponse("Reponse 2 question 1", true, new Date(), question1);
		
		Reponse rep1_2 = new Reponse("Reponse 1 question 2", true, new Date(), question2);
		Reponse rep2_2 = new Reponse("Reponse 2 question 2", false, new Date(), question2);
		
		Reponse rep1_3 = new Reponse("Reponse 1 question 3", false, new Date(), question3);
		Reponse rep2_3 = new Reponse("Reponse 2 question 3", true, new Date(), question3);
		
		Reponse rep1_4 = new Reponse("Reponse 1 question 4", false, new Date(), question4);
		Reponse rep2_4 = new Reponse("Reponse 2 question 4", true, new Date(), question4);
		
		Reponse rep1_5 = new Reponse("Reponse 1 question 5", false, new Date(), question5);
		Reponse rep2_5 = new Reponse("Reponse 2 question 5", true, new Date(), question5);
		
		Reponse rep1_6 = new Reponse("Reponse 1 question 6", false, new Date(), question6);
		Reponse rep2_6 = new Reponse("Reponse 2 question 6", true, new Date(), question6);
		
		Reponse rep1_7 = new Reponse("Reponse 1 question 7", false, new Date(), question7);
		Reponse rep2_7 = new Reponse("Reponse 2 question 7", true, new Date(), question7);
		
		Reponse rep1_8 = new Reponse("Reponse 1 question 8", false, new Date(), question8);
		Reponse rep2_8 = new Reponse("Reponse 2 question 8", true, new Date(), question8);
		
		Reponse rep1_9 = new Reponse("Reponse 1 question 9", false, new Date(), question9);
		Reponse rep2_9 = new Reponse("Reponse 2 question 9", true, new Date(), question9);
		
		repDao.save(rep1_1);repDao.save(rep2_1);
		repDao.save(rep1_2);repDao.save(rep2_2);
		repDao.save(rep1_3);repDao.save(rep2_3);
		repDao.save(rep1_4);repDao.save(rep2_4);
		repDao.save(rep1_5);repDao.save(rep2_5);
		repDao.save(rep1_6);repDao.save(rep2_6);
		repDao.save(rep1_7);repDao.save(rep2_7);
		repDao.save(rep1_8);repDao.save(rep2_8);
		repDao.save(rep1_9);repDao.save(rep2_9);
		
		
		
		ReponseEleve repE1_1 = new ReponseEleve("reponse eleve 1 question 1 ", user1, question1);
		ReponseEleve repE1_2 = new ReponseEleve("reponse eleve 1 question  2", user1, question2);
		ReponseEleve repE1_3 = new ReponseEleve("reponse eleve 1 question  3", user1, question3);
		
		ReponseEleve repE2_4 = new ReponseEleve("reponse eleve 2 question 4 ", user2, question4);
		ReponseEleve repE2_5 = new ReponseEleve("reponse eleve 2 question  5", user2, question5);
		ReponseEleve repE2_6 = new ReponseEleve("reponse eleve 2 question  6", user2, question6);
		
		ReponseEleve repE3_7 = new ReponseEleve("reponse eleve 3 question 7 ", user3, question7);
		ReponseEleve repE3_8 = new ReponseEleve("reponse eleve 3 question 8 ", user3, question8);
		ReponseEleve repE3_9 = new ReponseEleve("reponse eleve 3 question 9 ", user3, question9);
		
		repEleveDao.save(repE1_1);
		repEleveDao.save(repE1_2); 
		repEleveDao.save(repE1_3);
		repEleveDao.save(repE2_4);
		repEleveDao.save(repE2_5);
		repEleveDao.save(repE2_6);
		repEleveDao.save(repE3_7);
		repEleveDao.save(repE3_8);
		repEleveDao.save(repE3_9);
		
		
		List<Quizz> quizzs = quizzDao.findAll();
		for(Quizz qz:quizzs) {
			Set<Question> questions = qz.getQuestions();
			System.out.println("----" +qz.getMatiere() +" " + qz.getNiveau());
			for(Question qt:questions) {
				System.out.println("\t "+qt.getQuestion());
				Set<Indice> indices = qt.getIndices();
				for(Indice ind:indices) {
					System.out.println("\t *****"+ind.getIndice());
				}
				
				Set<Reponse> reps = qt.getReponses();
				for(Reponse rep:reps) {
					System.out.println("\t ########"+rep.getReponse() +" isCorrect = "+rep.isCorrect());
				}
			}
		}
		
		Set<ReponseEleve> repsE1 = userDao.findById(user2.getId_user()).get().getReponses();
		System.out.println("############################################################");
		for(ReponseEleve repE:repsE1) {
			System.out.println("\t ########"+repE.getReponse_eleve() );
		}
		System.out.println("############################################################");
		
		Set<Quizz> quizzsU3 = userDao.findById(user3.getId_user()).get().getQuizzs();
		System.out.println("############################################################");
		for(Quizz q:quizzsU3) {
			System.out.println("\t ########"+q.getMatiere() );
		}
		System.out.println("############################################################");
		
		
	}
}
