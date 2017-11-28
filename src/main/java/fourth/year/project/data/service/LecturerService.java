package fourth.year.project.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fourth.year.project.data.dao.LecturerRepository;
import fourth.year.project.data.model.Lecturer;

@Service
public class LecturerService {
	
	@Autowired
	private LecturerRepository lecturerRepository;
	
	
	public List<Lecturer> findAllLecturers(){
		return lecturerRepository.findAll();
		
	}

	public LecturerService() {
		// TODO Auto-generated constructor stub
	}

}
