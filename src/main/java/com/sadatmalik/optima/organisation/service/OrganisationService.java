package com.sadatmalik.optima.organisation.service;

import com.sadatmalik.optima.organisation.events.source.SimpleSourceBean;
import com.sadatmalik.optima.organisation.model.Organisation;
import com.sadatmalik.optima.organisation.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Simple Crud service class.
 *
 * Uses an injected SimpleSourceBean to publish change event messages.
 *
 * @author sadatmalik
 */
@RequiredArgsConstructor
@Service
public class OrganisationService {

    private final OrganisationRepository repository;
    private final SimpleSourceBean simpleSourceBean;

    public Organisation findById(String organisationId) {
        Optional<Organisation> opt = repository.findById(organisationId);
        simpleSourceBean.publishOrganisationChange("GET",
                organisationId);
        return opt.orElse(null);
    }

    public Organisation create(Organisation organisation){
        organisation.setId( UUID.randomUUID().toString());
        organisation = repository.save(organisation);
        simpleSourceBean.publishOrganisationChange("SAVE",
                organisation.getId());
        return organisation;
    }

    public void update(Organisation organisation){
        repository.save(organisation);
        simpleSourceBean.publishOrganisationChange("UPDATE",
                organisation.getId());
    }

    public void delete(Organisation organisation){
        repository.deleteById(organisation.getId());
        simpleSourceBean.publishOrganisationChange("DELETE",
                organisation.getId());
    }

    public void delete(String organisationId){
        repository.deleteById(organisationId);
        simpleSourceBean.publishOrganisationChange("DELETE",
                organisationId);
    }


}