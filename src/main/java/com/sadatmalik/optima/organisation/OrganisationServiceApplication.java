package com.sadatmalik.optima.organisation;

import com.sadatmalik.optima.organisation.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Every time organisation data is added, updated, or deleted, the organisation service will
 * publish a message to a Kafka topic, indicating that an organisation change event has occurred.
 *
 * The published message will include the organisation ID associated with the change event and
 * what action occurred (add, update, or delete).
 *
 * The @EnableBinding tells our application that it’s going to bind to a Spring Cloud Stream
 * message broker. The use of Source.class in @EnableBinding tells Spring Cloud Stream that this
 * service will communicate with the message broker via a set of channels defined in the Source
 * class.
 *
 * Channels sit above a message queue. Spring Cloud Stream has a default set of channels that
 * can be configured to speak to a message broker.
 *
 * @author sadatmalik
 */
@SpringBootApplication
@RefreshScope
@EnableEurekaClient
@EnableBinding(Source.class)
public class OrganisationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganisationServiceApplication.class, args);
	}

	/**
	 * Creates the Load Balancer–backed Spring RestTemplate bean. Used by the
	 * service.client.OrganisationRestTemplateClient.
	 *
	 * We add a UserContextInterceptor to the RestTemplate.
	 *
	 * @return load balancer-backed rest template.
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List interceptors = template.getInterceptors();
		if (interceptors == null) {
			template.setInterceptors(
					Collections.singletonList(
							new UserContextInterceptor()));
		} else {
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}

		return template;
	}

}
