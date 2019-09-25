package vn.topica.itlab4.jdbc.entity.db;


import vn.topica.itlab4.jdbc.annotation.Column;
import vn.topica.itlab4.jdbc.annotation.OneToMany;
import vn.topica.itlab4.jdbc.annotation.Table;

import java.util.List;

@Table(name = "class")
public class ClassEntity {
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "student")
	private List<StudentEntity> listStudent;

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

	public List<StudentEntity> getListStudent() {
		return listStudent;
	}

	public void setListStudent(List<StudentEntity> listStudent)
	{
		this.listStudent = listStudent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
