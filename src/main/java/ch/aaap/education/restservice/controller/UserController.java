package ch.aaap.education.restservice.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.aaap.education.restservice.domain.User;
import ch.aaap.education.restservice.service.OrganizationService;
import ch.aaap.education.restservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class UserNotFoundException extends RuntimeException {

		public UserNotFoundException(Long userId) {
			super("could not find user '" + userId + "'.");
		}
	}
	
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> get(){
		return new ResponseEntity<List<User>>(userService.findAllUsers(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") Long id){
		this.checkUser(id);
		return new ResponseEntity<User>(userService.findById(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("email") String email){
		//this.checkUser(id);
		return new ResponseEntity<User>(userService.findByEmail(email),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/organization/{organizationId}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findByOrganizationId(@PathVariable("organizationId") Long id){
		//this.checkUser(id);
		return new ResponseEntity<List<User>>(userService.findByOrganizationId(id),HttpStatus.OK);
	}
	
	//Das muss auch besser gehen?
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody HashMap<String,String> user){
		User newUser = new User(user.get("name"),user.get("email"),user.get("password"),organizationService.findById(Long.parseLong(user.get("organization"))));
		return new ResponseEntity<User>(userService.saveUser(newUser),HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("userId") Long id, @RequestBody User user){
		User existingUser = userService.findById(id);
		if(existingUser == null) 
			throw new UserNotFoundException(id);
		
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		
		return new ResponseEntity<User>(userService.saveUser(existingUser),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllUsers(){
		userService.deleteAllUsers();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeUser(@PathVariable("userId") Long id){
		this.checkUser(id);
		userService.deleteUserById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private void checkUser(Long id) {
		if(this.userService.findById(id) == null) 
			throw new UserNotFoundException(id);
	}
}
