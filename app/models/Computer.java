package models;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import models.frame.Page;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.jpa.JPA;

/**
 * Computer entity managed by JPA
 */
@Entity 
@SequenceGenerator(name = "computer_seq", sequenceName = "computer_seq")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "computer_seq")
    public Long id;
    
    @Constraints.Required
    public String name;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date introduced;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date discontinued;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    public Company company;
    
    /**
     * Find a company by id.
     */
    public static Computer findById(Long id) {
        return JPA.em().find(Computer.class, id);
    }
    
    /**
     * Update this computer.
     */
    public void update(Long id) {
        if(this.company.id == null) {
            this.company = null;
        } else {
            this.company = Company.findById(company.id);
        }
        this.id = id;
        JPA.em().merge(this);
    }
    
    /**
     * Insert this new computer.
     */
    public void save() {
        if(this.company.id == null) {
            this.company = null;
        } else {
            this.company = Company.findById(company.id);
        }
        this.id = id;
        JPA.em().persist(this);
    }
    
    /**
     * Delete this computer.
     */
    public void delete() {
        JPA.em().remove(this);
    }
     
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page page(int page, int pageSize, String sortBy, String order, String filter) {
        if(page < 1) page = 1;
        Long total = (Long)JPA.em()
            .createQuery("select count(c) from Computer c where lower(c.name) like ?")
            .setParameter(1, "%" + filter.toLowerCase() + "%")
            .getSingleResult();
        List<Computer> data = JPA.em()
            .createQuery("from Computer c where lower(c.name) like ? order by c." + sortBy + " " + order)
            .setParameter(1, "%" + filter.toLowerCase() + "%")
            .setFirstResult((page - 1) * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
        return new Page(data, total, page, pageSize);
    }
}

