package ch.aaap.education.restservice.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Organization {
	@OneToMany(mappedBy = "organization")
    private Set<User> users = new HashSet<>();

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	
	protected Organization() {
	}
	
	public Organization(String name){
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Organization[id=%d, name='%s']",
                id, name);
    }

}
