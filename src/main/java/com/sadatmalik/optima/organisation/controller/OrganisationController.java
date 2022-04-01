package com.sadatmalik.optima.organisation.controller;

import com.sadatmalik.optima.organisation.model.Organisation;
import com.sadatmalik.optima.organisation.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The microservice controller exposing basic Crud endpoints.
 *
 * @author sadatmalik
 */
@RestController
@RequestMapping(value="v1/organization")
public class OrganisationController {

    @Autowired
    private OrganisationService service;


    @RequestMapping(value="/{organisationId}",method = RequestMethod.GET)
    public ResponseEntity<Organisation> getOrganization(
            @PathVariable("organisationId") String organisationId) {
        return ResponseEntity.ok(service.findById(organisationId));
    }

    @RequestMapping(value="/{organisationId}",method = RequestMethod.PUT)
    public void updateOrganisation(
            @PathVariable("organisationId") String id,
            @RequestBody Organisation organisation) {
        service.update(organisation);
    }

    @PostMapping
    public ResponseEntity<Organisation> saveOrganisation(
            @RequestBody Organisation organisation) {
        return ResponseEntity.ok(service.create(organisation));
    }

    @RequestMapping(value="/{organizationId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganisation(
            @PathVariable("id") String id,
            @RequestBody Organisation organisation) {
        service.delete(organisation);
    }

}