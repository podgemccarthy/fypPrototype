package fourth.year.project.data.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	private Integer userId;
	private String firstName;
	private String lastName;
	private String gender;
	private Double latitude;
	private Double longitude;
	private Course course;
	private Set<Timetable> userTimetable;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "user_latitude")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "user_longitude")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	// @JsonIgnore
	// public Set<UserTimetable> getUserTimetable() {
	// return userTimetable;
	// }
	//
	// public void setUserTimetable(Set<UserTimetable> userTimetable) {
	// this.userTimetable = userTimetable;
	// }

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usertimetable", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "entry_id", referencedColumnName = "id"))
	@JsonIgnore
	public Set<Timetable> getUserTimetable() {
		return userTimetable;
	}

	public void setUserTimetable(Set<Timetable> userTimetable) {
		this.userTimetable = userTimetable;
	}

}
