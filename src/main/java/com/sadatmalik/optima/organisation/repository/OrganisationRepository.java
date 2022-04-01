package com.sadatmalik.optima.organisation.repository;

import com.sadatmalik.optima.organisation.model.Organisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Crud interface for organisation repo.
 *
 * @author sadatmalik
 */
@Repository
public interface OrganisationRepository extends CrudRepository<Organisation,String> {
    public Optional<Organisation> findById(String organizationId);
}