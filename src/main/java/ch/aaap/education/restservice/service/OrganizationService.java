package ch.aaap.education.restservice.service;

import java.util.List;

import ch.aaap.education.restservice.domain.Organization;

public interface OrganizationService {
     
    Organization findById(long id);
     
    List<Organization> findByName(String name);
     
    Organization saveOrganization(Organization org);
     
    Organization updateOrganization(Organization org);
     
    void deleteOrganizationById(long id);
 
    List<Organization> findAllOrganizations(); 
     
    void deleteAllOrganizations();
          
}
