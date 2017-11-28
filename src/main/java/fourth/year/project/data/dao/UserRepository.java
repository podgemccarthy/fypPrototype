package fourth.year.project.data.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fourth.year.project.data.model.User;

public interface UserRepository  extends JpaRepository<User, Integer> {
	
	//put your querie here 
	
}
