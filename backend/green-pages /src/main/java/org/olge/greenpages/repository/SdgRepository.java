package org.olge.greenpages.repository;

import org.olge.greenpages.model.Sdg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SdgRepository extends JpaRepository<Sdg, Long> {
}