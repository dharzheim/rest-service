package ch.aaap.education.restservice.service;

import java.util.List;

import ch.aaap.education.restservice.domain.User;

public interface UserService {
	User findById(long id);
    
    List<User> findByName(String name);
    
    User findByEmail(String email);
    
    List<User> findByOrganizationId(Long id);
     
    User saveUser(User user);
     
    User updateUser(User user);
     
    void deleteUserById(long id);
 
    List<User> findAllUsers(); 
     
    void deleteAllUsers();
}
