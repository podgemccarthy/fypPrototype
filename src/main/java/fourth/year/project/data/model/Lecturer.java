package fourth.year.project.data.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="lecturer")
public class Lecturer {

	private Integer lecturerId;
	private String lecturerName;
	private Set<Module> lectureModules;
	private Set<Timetable> lectureTimetable;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getLecturerId() {
		return lecturerId;
	}
	public void setLecturerId(Integer lecturerId) {
		this.lecturerId = lecturerId;
	}
	@Column(name = "name")
	public String getLecturerName() {
		return lecturerName;
	}
	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	 @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
	 @JsonIgnore
	public Set<Module> getLectureModules() {
		return lectureModules;
	}
	public void setLectureModules(Set<Module> lectureModules) {
		this.lectureModules = lectureModules;
	}
	 @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
	 @JsonIgnore
	public Set<Timetable> getLectureTimetable() {
		return lectureTimetable;
	}
	public void setLectureTimetable(Set<Timetable> lectureTimetable) {
		this.lectureTimetable = lectureTimetable;
	}

}
