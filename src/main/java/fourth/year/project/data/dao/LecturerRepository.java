package fourth.year.project.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fourth.year.project.data.model.Lecturer;

public interface LecturerRepository extends  JpaRepository<Lecturer, Integer> {

}
