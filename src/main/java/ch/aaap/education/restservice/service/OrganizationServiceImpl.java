package ch.aaap.education.restservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.aaap.education.restservice.domain.Organization;
import ch.aaap.education.restservice.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository; 
	
	@Override
	public Organization findById(long id) {
		return organizationRepository.findOne(id);
	}

	@Override
	public List<Organization> findByName(String name) {
		return organizationRepository.findByName(name);
	}

	@Override
	public Organization saveOrganization(Organization org) {
		return organizationRepository.save(org);
	}

	@Override
	public Organization updateOrganization(Organization org) {
		return organizationRepository.save(org);
	}

	@Override
	public void deleteOrganizationById(long id) {
		organizationRepository.delete(id);

	}

	@Override
	public List<Organization> findAllOrganizations() {
		return (List<Organization>) organizationRepository.findAll();
	}

	@Override
	public void deleteAllOrganizations() {
		organizationRepository.deleteAll();

	}

}
