package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Employee extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Constraints.Required
	public String name;

	public static Finder<Long, Employee> find = new Finder<Long, Employee>(Long.class,
			Employee.class);

	public static List<Employee> all() {
		return find.all();
	}
}
