package com.sadatmalik.optima.organisation.controller;

import com.sadatmalik.optima.organisation.model.Organisation;
import com.sadatmalik.optima.organisation.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * The microservice controller exposing basic Crud endpoints.
 *
 * Note we lock down the DELETE call on the organisation service to only those users with
 * ADMIN access.
 *
 * @author sadatmalik
 */
@RestController
@RequestMapping(value="v1/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationService service;

    /**
     * Only users with the USER and ADMIN roles can execute this action.
     *
     * @param organisationId
     * @return
     */
    @RolesAllowed({ "ADMIN", "USER" })
    @RequestMapping(value="/{organisationId}",method = RequestMethod.GET)
    public ResponseEntity<Organisation> getOrganization(
            @PathVariable("organisationId") String organisationId) {
        return ResponseEntity.ok(service.findById(organisationId));
    }

    /**
     * Only users with the USER and ADMIN roles can execute this action.
     *
     * @param id
     * @param organisation
     */
    @RolesAllowed({ "ADMIN", "USER" })
    @RequestMapping(value="/{organisationId}",method = RequestMethod.PUT)
    public void updateOrganisation(
            @PathVariable("organisationId") String id,
            @RequestBody Organisation organisation) {
        service.update(organisation);
    }

    /**
     * Only users with the USER and ADMIN roles can execute this action.
     *
     * @param organisation
     * @return
     */
    @RolesAllowed({ "ADMIN", "USER" })
    @PostMapping
    public ResponseEntity<Organisation> saveOrganisation(
            @RequestBody Organisation organisation) {
        return ResponseEntity.ok(service.create(organisation));
    }

    /**
     * Only users with the ADMIN role can execute this action.
     *
     * @param id
     */
    @RolesAllowed("ADMIN")
    @RequestMapping(value="/{organisationId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganisation(
            @PathVariable("organisationId") String id) {
        service.delete(id);
    }

}