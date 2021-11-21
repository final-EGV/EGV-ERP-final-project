package org.erp.egv.theater.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE")
@SequenceGenerator(name = "MOVIE_SEQ_GENERATOR",
					sequenceName = "SEQ_MOVIE_CODE",
					initialValue = 1,
					allocationSize = 1)
public class MovieDTO implements Serializable {
	private static final long serialVersionUID = 5141036429529659038L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "MOVIE_SEQ_GENERATOR")
	@Column(name = "MOVIE_CODE")
	private int code;
	
	@Column(name = "MOVIE_NAME")
	private String name;

	@Column(name = "OPENING_DATE")
	private java.sql.Date openingDate;

	@Column(name = "RUNNINGTIME")
	private int runningTime;

	@Column(name = "GRADE")
	private String grade;

	@Column(name = "GENRE")
	private String genre;

	@Column(name = "DISTRIBUTOR")
	private String distributor;

	@Column(name = "DIRECTOR")
	private String director;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "POSTER_ORIG_NAME")
	private String posterOrigName;

	@Column(name = "POSTER_UUID_NAME")
	private String posterUuidName;

	@Column(name = "POSTER_IMG_PATH")
	private String posterImgPath;

	@Column(name = "OPENING_YN", columnDefinition = "default = 'N'")
	private String openingYn;
	
	// association field right below could be deprecated if useless.
	@OneToMany(mappedBy = "movieAndScreening")
	private List<ScreeningScheduleDTO> screeningScheduleList = new ArrayList<>();
	
	// association field right below could be deprecated if useless.
	@OneToMany(mappedBy = "movieAndEvent")
	private List<EventDTO> eventList = new ArrayList<>();

	public MovieDTO() {
	}

	public MovieDTO(int code, String name, Date openingDate, int runningTime, String grade, String genre,
			String distributor, String director, String country, String posterOrigName, String posterUuidName,
			String posterImgPath, String openingYn, List<ScreeningScheduleDTO> screeningScheduleList,
			List<EventDTO> eventList) {
		this.code = code;
		this.name = name;
		this.openingDate = openingDate;
		this.runningTime = runningTime;
		this.grade = grade;
		this.genre = genre;
		this.distributor = distributor;
		this.director = director;
		this.country = country;
		this.posterOrigName = posterOrigName;
		this.posterUuidName = posterUuidName;
		this.posterImgPath = posterImgPath;
		this.openingYn = openingYn;
		this.screeningScheduleList = screeningScheduleList;
		this.eventList = eventList;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(java.sql.Date openingDate) {
		this.openingDate = openingDate;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPosterOrigName() {
		return posterOrigName;
	}

	public void setPosterOrigName(String posterOrigName) {
		this.posterOrigName = posterOrigName;
	}

	public String getPosterUuidName() {
		return posterUuidName;
	}

	public void setPosterUuidName(String posterUuidName) {
		this.posterUuidName = posterUuidName;
	}

	public String getPosterImgPath() {
		return posterImgPath;
	}

	public void setPosterImgPath(String posterImgPath) {
		this.posterImgPath = posterImgPath;
	}

	public String getOpeningYn() {
		return openingYn;
	}

	public void setOpeningYn(String openingYn) {
		this.openingYn = openingYn;
	}

	public List<ScreeningScheduleDTO> getScreeningScheduleList() {
		return screeningScheduleList;
	}

	public void setScreeningScheduleList(List<ScreeningScheduleDTO> screeningScheduleList) {
		this.screeningScheduleList = screeningScheduleList;
	}

	public List<EventDTO> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventDTO> eventList) {
		this.eventList = eventList;
	}

	@Override
	public String toString() {
		return "MovieDTO [code=" + code + ", name=" + name + ", openingDate=" + openingDate + ", runningTime="
				+ runningTime + ", grade=" + grade + ", genre=" + genre + ", distributor=" + distributor + ", director="
				+ director + ", country=" + country + ", posterOrigName=" + posterOrigName + ", posterUuidName="
				+ posterUuidName + ", posterImgPath=" + posterImgPath + ", openingYn=" + openingYn
				+ ", screeningScheduleList=" + screeningScheduleList + ", eventList=" + eventList + "]";
	}
	
}
