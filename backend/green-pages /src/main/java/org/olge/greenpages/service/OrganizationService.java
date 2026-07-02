package org.olge.greenpages.service;

import org.olge.greenpages.model.Organization;
import org.olge.greenpages.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    public OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository=organizationRepository;
    }
    public List<Organization> getApprovedOrganizations(){
        return organizationRepository.findAll().stream().filter(org -> "approved".equals(org.getStatus()) ).toList();
    }
    public Organization submitOrganization(Organization organization){
        organization.setStatus("pending");
        organization.setUpdatedAt(LocalDateTime.now());
        organization.setCreatedAt(LocalDateTime.now());
        return organizationRepository.save(organization);
    }
    public List<Organization> getPendingOrganizations(){
        return organizationRepository.findAll().stream().filter(org -> "pending".equals(org.getStatus())).toList();
    }
    public Organization approveOrganization(Long id){
        Organization org=organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found"));
        org.setStatus("approved");
        org.setUpdatedAt(LocalDateTime.now());
        return organizationRepository.save(org);
    }
    public Organization rejectOrganization(Long id){
        Organization org=organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found"));
        org.setStatus("rejected");
        org.setUpdatedAt(LocalDateTime.now());
        return organizationRepository.save(org);
    }
    public void deleteOrganization(Long id){
        organizationRepository.deleteById(id);
    }
    public List<Organization> search(String q, String country, String city, String organizationType, String offeringType) {
        return organizationRepository.search(
                q == null || q.isBlank() ? null : q,
                country == null || country.isBlank() ? null : country,
                city == null || city.isBlank() ? null : city,
                organizationType == null || organizationType.isBlank() ? null : organizationType,
                offeringType == null || offeringType.isBlank() ? null : offeringType
        );
    }
}
