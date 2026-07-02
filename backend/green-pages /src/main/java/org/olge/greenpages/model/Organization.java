package org.olge.greenpages.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;
    private String description;

    @Column(nullable = false)
    private String email;
    private String website;

    @Column(nullable = false)
    private String country;
    private String city;
    private String organizationType;
    private String offeringType;
    private String logoUrl;
    private String linkedin;

    @Column(columnDefinition = "varchar(20) default 'pending'")
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getEmail(){return email;}

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public String getOfferingType() {
        return offeringType;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setOfferingType(String offeringType) {
        this.offeringType = offeringType;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
