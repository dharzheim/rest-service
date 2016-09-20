package ch.aaap.education.restservice.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.aaap.education.restservice.domain.Organization;
import ch.aaap.education.restservice.domain.User;
import ch.aaap.education.restservice.service.OrganizationService;
import ch.aaap.education.restservice.service.UserService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class OrganizationNotFoundException extends RuntimeException {

		public OrganizationNotFoundException(Long organizationId) {
			super("could not find organization '" + organizationId + "'.");
		}
	}
	
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Organization>> get(){
		return new ResponseEntity<List<Organization>>(organizationService.findAllOrganizations(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
	public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") Long id){
		this.checkOrganization(id);
		return new ResponseEntity<Organization>(organizationService.findById(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{organizationId}/user", method = RequestMethod.GET)
	public ResponseEntity<Set<User>> getUsers(@PathVariable("organizationId") Long id){
		this.checkOrganization(id);
		return new ResponseEntity<Set<User>>(organizationService.findById(id).getUsers(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{organizationId}/user", method = RequestMethod.POST)
	public ResponseEntity<User> getUsers(@PathVariable("organizationId") Long id, @RequestBody User user){
		user.setOrganization(organizationService.findById(id));
		return new ResponseEntity<User>(userService.saveUser(user),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Organization> createOrganization(@RequestBody Organization org){
		return new ResponseEntity<Organization>(organizationService.saveOrganization(org),HttpStatus.CREATED);
	}
	
	//Möglich inkonsistenz zwischen URL und id aus RequestBody?
	@RequestMapping(value = "/{organizationId}", method = RequestMethod.PUT)
	public ResponseEntity<Organization> updateOrganization(@PathVariable("organizationId") Long id, @RequestBody Organization org){
		Organization existingOrg = organizationService.findById(id);
		if(existingOrg == null) 
			throw new OrganizationNotFoundException(id);
		
		existingOrg.setName(org.getName());
		return new ResponseEntity<Organization>(organizationService.saveOrganization(existingOrg),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllOrganizations(){
		organizationService.deleteAllOrganizations();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeOrganization(@PathVariable("organizationId") Long id){
		this.checkOrganization(id);
		organizationService.deleteOrganizationById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private void checkOrganization(Long id) {
		if(this.organizationService.findById(id) == null) 
			throw new OrganizationNotFoundException(id);
	}
}
