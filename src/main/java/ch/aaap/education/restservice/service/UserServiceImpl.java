package ch.aaap.education.restservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.aaap.education.restservice.domain.User;
import ch.aaap.education.restservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public User findById(long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(long id) {
		userRepository.delete(id);

	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();

	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findByOrganizationId(Long id) {
		return userRepository.findByOrganizationId(id);
	}

}
