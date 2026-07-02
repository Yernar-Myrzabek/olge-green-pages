package org.olge.greenpages.repository;

import org.olge.greenpages.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    @Query(value = """
    SELECT * FROM organizations
    WHERE status = 'approved'
    AND (:q IS NULL OR
        LOWER(name) LIKE LOWER(CONCAT('%', :q, '%')) OR
        LOWER(description) LIKE LOWER(CONCAT('%', :q, '%')))
    AND (:country IS NULL OR LOWER(country) = LOWER(:country))
    AND (:city IS NULL OR LOWER(city) = LOWER(:city))
    AND (:organizationType IS NULL OR LOWER(organization_type) = LOWER(:organizationType))
    AND (:offeringType IS NULL OR LOWER(offering_type) = LOWER(:offeringType))
    """, nativeQuery = true)
    List<Organization> search(
            @Param("q") String q,
            @Param("country") String country,
            @Param("city") String city,
            @Param("organizationType") String organizationType,
            @Param("offeringType") String offeringType
    );
}
