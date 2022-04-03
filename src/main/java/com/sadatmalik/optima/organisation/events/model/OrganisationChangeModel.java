package com.sadatmalik.optima.organisation.events.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The OrganizationChangeModel class declares three data elements:
 *
 * 1. action — this is the action that triggered the event. We include the action element in the
 * message to give the message consumer more context on how it should process an event.
 *
 * 2. organisationId — this is the organisation ID associated with the event.
 *
 * 3. correlationId — this is the correlation ID of the service call that triggered the event.
 * We include a correlation ID in our events as it helps with tracking and debugging the flow
 * of messages through our services.
 *
 * @author sadatmalik
 */
@Getter
@Setter
@ToString
public class OrganisationChangeModel {
    private String type;
    private String action;
    private String organisationId;
    private String correlationId;

    public OrganisationChangeModel(String type,
                                   String action, String organisationId,
                                   String correlationId) {
        this.type = type;
        this.action = action;
        this.organisationId = organisationId;
        this.correlationId = correlationId;
    }

}
