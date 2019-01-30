package projet;


import projet.dao.UserRepository;
import projet.entities.*;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class ProjetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ProjetApplication.class, args);
		UserRepository userDao = ctx.getBean(UserRepository.class);
		
		userDao.save(new User("bahammou","mohamed","mohamed@gmail.com","mohamed123","mohamed123"));
		userDao.save(new User("alla","reda","reda@gmail.com","reda123","reda123"));
		userDao.save(new User("belghazi","zouhair","zouhair@gmail.com","zouhair123","zouhair123"));
		
		
		List<User> users = userDao.findAll();
		for(User u:users) {
			System.out.println(u.toString());
		}
	}
}
