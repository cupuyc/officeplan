package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.frame.Page;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Office extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Constraints.Required
	public String name;
	
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date introduced;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date discontinued;
    
	public static List<Office> all() {
		return find.all();
	}

	public static Finder<Long, Office> find = new Finder<Long, Office>(
			Long.class, Office.class);

	public static Page<Office> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return new Page(
				find.where()
					.ilike("name", "%" + filter + "%")
					.orderBy(sortBy + " " + order)
					.findPagingList(pageSize)
					.getPage(page), 
				pageSize);
	}
}
