package projet;


import projet.dao.QuestionRepository;
import projet.dao.QuizzRepository;
import projet.dao.UserRepository;
import projet.entities.*;

import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class ProjetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ProjetApplication.class, args);
		UserRepository userDao = ctx.getBean(UserRepository.class);
		QuestionRepository questionDao = ctx.getBean(QuestionRepository.class);
		QuizzRepository quizzDao = ctx.getBean(QuizzRepository.class);
		
		Quizz quizz1 = new Quizz("bac", "math", "seance 1", new Date());
		Quizz quizz2 = new Quizz("cm1", "physique", "seance 2", new Date());
		Quizz quizz3 = new Quizz("cm2", "science", "seance 4", new Date());
		
		quizzDao.save(quizz1);
		quizzDao.save(quizz2);
		quizzDao.save(quizz3);
		
		questionDao.save(new Question(new Date(), "question 1", "media", 2, quizz1));
		questionDao.save(new Question(new Date(), "question 2", "simple", 4, quizz1));
		questionDao.save(new Question(new Date(), "question 3", "media", 5, quizz1));
		
		
		questionDao.save(new Question(new Date(), "question 1_2", "media", 4, quizz2));
		questionDao.save(new Question(new Date(), "question 2_2", "simple", 8, quizz2));
		questionDao.save(new Question(new Date(), "question 3_2", "media", 1, quizz2));
		
		questionDao.save(new Question(new Date(), "question 1_3", "media", 1, quizz3));
		questionDao.save(new Question(new Date(), "question 2_3", "simple", 3, quizz3));
		questionDao.save(new Question(new Date(), "question 3_3", "media", 6, quizz3));
		
		
		
		
		userDao.save(new User("bahammou","mohamed","mohamed@gmail.com","mohamed123","mohamed123"));
		userDao.save(new User("alla","reda","reda@gmail.com","reda123","reda123"));
		userDao.save(new User("belghazi","zouhair","zouhair@gmail.com","zouhair123","zouhair123"));
		
		
		List<User> users = userDao.findAll();
		for(User u:users) {
			System.out.println(u.toString());
		}
	}
}
