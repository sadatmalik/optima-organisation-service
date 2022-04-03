package com.sadatmalik.optima.organisation.events.source;

import com.sadatmalik.optima.organisation.events.model.OrganisationChangeModel;
import com.sadatmalik.optima.organisation.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * This class creates the logic to publish messages.
 *
 * It injects a Source interface implementation for use by the service. All communication to a
 * specific message topic occurs through a Spring Cloud Stream construct called a channel, which
 * is represented by a Java interface. We use the Source interface, which exposes a single
 * method called output().
 *
 * The Source interface is convenient to use when our service only needs to publish to a single
 * channel. The output() method returns a class of type MessageChannel. With this type, we send
 * messages to the message broker.
 *
 * @author sadatmalik
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SimpleSourceBean {
    private final Source source;

    /**
     * Builds and publishes a Java POJO message called OrganisationChangeModel.
     *
     * Sends the message from a channel defined in the Source class.
     *
     * The ActionEnum passed by the parameters in the output() method contains the following
     * actions:
     *
     * public enum ActionEnum {
     *    GET,
     *    CREATED,
     *    UPDATED,
     *    DELETED
     * }
     *
     * When we’re ready to publish the message, we use the send() method on the MessageChannel
     * class returned from the source.output(). We use a Spring helper class, called
     * MessageBuilder, to take the contents of the OrganizationChangeModel class and convert
     * it to a Spring Message class.
     *
     * The application configuration property spring.cloud.stream.bindings.output maps the
     * source.output() channel to the orgChangeTopic on the message broker we will communicate
     * with.
     *
     * @param action
     * @param organisationId
     */
    public void publishOrganisationChange(String action, String organisationId){
        log.debug("Sending Kafka message {} for Organisation Id: {}",
                action, organisationId);
        OrganisationChangeModel change =  new OrganisationChangeModel(
                OrganisationChangeModel.class.getTypeName(),
                action,
                organisationId,
                UserContext.getCorrelationId());

        source.output().send(
                MessageBuilder
                        .withPayload(change)
                        .build());
    }
}