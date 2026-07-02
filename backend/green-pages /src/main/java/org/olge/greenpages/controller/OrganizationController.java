package org.olge.greenpages.controller;

import org.olge.greenpages.model.Organization;
import org.olge.greenpages.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@CrossOrigin(origins = "*")
public class OrganizationController {
    private final OrganizationService organizationService;
    public OrganizationController(OrganizationService organizationService){
        this.organizationService=organizationService;
    }
    @GetMapping
    public List<Organization> getApproved(){
        return organizationService.getApprovedOrganizations();
    }

    @PostMapping
    public ResponseEntity<Organization> submit(@RequestBody Organization organization) {
        Organization saved = organizationService.submitOrganization(organization);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/pending")
    public List<Organization> getPending() {
        return organizationService.getPendingOrganizations();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Organization> approve(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.approveOrganization(id));
    }
    @PutMapping("{id}/reject")
    public ResponseEntity<Organization> reject(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.rejectOrganization(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public List<Organization> search(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String organizationType,
            @RequestParam(required = false) String offeringType) {
        return organizationService.search(q, country, city, organizationType, offeringType);
    }
}
