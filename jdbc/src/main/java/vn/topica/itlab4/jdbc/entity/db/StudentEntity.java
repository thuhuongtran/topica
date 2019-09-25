package vn.topica.itlab4.jdbc.entity.db;


import vn.topica.itlab4.jdbc.annotation.Column;
import vn.topica.itlab4.jdbc.annotation.JoinColumn;
import vn.topica.itlab4.jdbc.annotation.Table;
import vn.topica.itlab4.jdbc.entity.db.ClassEntity;

@Table(name = "student")
public class StudentEntity {
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;

	@JoinColumn(name = "class_id")
	private ClassEntity classEntity;

	public StudentEntity() {
	}

	public StudentEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
