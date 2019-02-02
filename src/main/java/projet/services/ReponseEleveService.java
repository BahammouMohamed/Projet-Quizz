package projet.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import projet.dao.ReponseEleveRepository;

@RestController
public class ReponseEleveService {
	@Autowired
	private ReponseEleveRepository repEleRepository ;
	
}
