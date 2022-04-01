package com.sadatmalik.optima.organisation.service;

import com.sadatmalik.optima.organisation.model.Organisation;
import com.sadatmalik.optima.organisation.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Simple Crud service class.
 *
 * @author sadatmalik
 */
@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository repository;

    public Organisation findById(String organisationId) {
        Optional<Organisation> opt = repository.findById(organisationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organisation create(Organisation organisation){
        organisation.setId( UUID.randomUUID().toString());
        organisation = repository.save(organisation);
        return organisation;

    }

    public void update(Organisation organisation){
        repository.save(organisation);
    }

    public void delete(Organisation organisation){
        repository.deleteById(organisation.getId());
    }
}