package ch.aaap.education.restservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.aaap.education.restservice.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public List<User> findByName(String name);
	public User findByEmail(String name);
	public List<User> findByOrganizationId(Long organizationId);
}
