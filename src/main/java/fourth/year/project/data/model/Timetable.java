package fourth.year.project.data.model;

import java.sql.Time;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "timetable")
public class Timetable {

	private Integer timeTableId;
	private String day;
	private Time timeStart;
	private Time timeEnd;
	private String location;
	private Module module;
	private Lecturer lecturer;
	private Set<User> userTimetable;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(Integer timeTableId) {
		this.timeTableId = timeTableId;
	}

	@Column(name = "day")
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Column(name = "time_start")
	public Time getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	@Column(name = "time_end")
	public Time getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}

	@Column(name = "location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@ManyToOne
	@JoinColumn(name = "module_id")
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@ManyToOne
	@JoinColumn(name = "lecturer")
	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	@ManyToMany(mappedBy = "userTimetable")
	@JsonIgnore
	public Set<User> getUserTimetable() {
		return userTimetable;
	}

	public void setUserTimetable(Set<User> userTimetable) {
		this.userTimetable = userTimetable;
	}

}
