package fourth.year.project.data.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "module")
public class Module {
	
	private String moduleId;
	private String moduleTitle;
	private Lecturer lecturer;
	private Course parentCourse;
	private Set<Timetable> timetable;
	
	@Id
	@Column(name = "id")
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	@Column (name="module_title")
	public String getModuleTitle() {
		return moduleTitle;
	}
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}
	
	@ManyToOne
  @JoinColumn(name = "lecturer_id")
	public Lecturer getLecturer() {
		return lecturer;
	}
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	@OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "parent_course_id")
	public Course getParentCourse() {
		return parentCourse;
	}
	public void setParentCourse(Course parentCourse) {
		this.parentCourse = parentCourse;
	}
	 @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
	 @JsonIgnore
	public Set<Timetable> getTimetable() {
		return timetable;
	}
	public void setTimetable(Set<Timetable> timetable) {
		this.timetable = timetable;
	}



}
