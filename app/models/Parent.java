package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.validation.NotNull;

@Entity
public class Parent extends Model {

	@Id
	public Long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    public List<Child> children = new ArrayList<Child>();

	@NotNull
	public String name;

	@CreatedTimestamp
	public Date createDate;

	@Version
	public Date updateDate;

	public String toString() {
		return "Parent [id=" + id + ", name=" + name + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
}
