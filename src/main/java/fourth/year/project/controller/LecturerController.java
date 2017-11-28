package fourth.year.project.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fourth.year.project.data.model.Lecturer;
import fourth.year.project.data.model.Module;
import fourth.year.project.data.service.LecturerService;

@RestController
public class LecturerController {

	@Autowired(required=true)
	private LecturerService lecturerService;
	
	@GetMapping("/lecturers")
	public String getAllLecturers(){
		return lecturerService.findAllLecturers().get(0).getLectureTimetable().iterator().next().getDay();
		}
	

}
