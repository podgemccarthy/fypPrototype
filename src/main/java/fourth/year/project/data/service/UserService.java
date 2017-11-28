package fourth.year.project.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fourth.year.project.data.model.User;
import fourth.year.project.data.dao.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	public User getUserById(Integer userId){
		return userRepository.findOne(userId);
	}
	public UserService() {
		// TODO Auto-generated constructor stub
	}

}
