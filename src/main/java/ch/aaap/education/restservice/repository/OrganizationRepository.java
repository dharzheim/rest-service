package ch.aaap.education.restservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.aaap.education.restservice.domain.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
	public List<Organization> findByName(String name);
}
